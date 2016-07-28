package city.model.manager;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.sql.Timestamp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.type.Type;
import org.json.simple.JSONObject;

import city.controller.access.SesionBean;
import city.model.dao.entidades.AudActividadAplicacion;

@SessionScoped
@ManagedBean
public class ActivityTrackerInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;
	private Set inserts = new HashSet();
	private Set updates = new HashSet();
	private Set deletes = new HashSet();
	boolean isMainEntity = false;

	// called when record deleted.
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		deletes.add(entity);
		isMainEntity = true;
		
	}

	// called when record updated
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		if (!(entity instanceof AudActividadAplicacion)) {
			updates.add(entity);
			isMainEntity = true;
			return true;
		}
		isMainEntity = false;
		return false;
	}

	// called on load events
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		// log loading events

		return true;
	}

	public void onPostUpdate(PostUpdateEvent postUpdateEvent) {

		if (!(postUpdateEvent.getEntity() instanceof AudActividadAplicacion)) {
			
			isMainEntity = true;

		}
		isMainEntity = false;

	}

	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		if (!(entity instanceof AudActividadAplicacion)) {

			inserts.add(entity);
			isMainEntity = true;
			
			return true;
		}

		isMainEntity = false;
		return false;
	}

	// called before commit into database
	public void preFlush(Iterator iterator) {
		
	}

	// called after committed into database
	public void postFlush(Iterator iterator) {

		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			SesionBean user = (SesionBean) session.getAttribute("sesionBean");

			String userName = user.getUsuario();

			HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
					.getExternalContext().getRequest();
			Configuration config = new Configuration();
			config.configure();
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties());
			SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
			Session sessionn = sessionFactory.openSession();

			String IP = httpServletRequest.getRemoteAddr();
			String appName = "YCP";
			String client = httpServletRequest.getHeader("User-Agent");
			Timestamp fecha = new Timestamp(new java.util.Date().getTime());
			generateAudit(inserts, userName, client, IP, appName, "INSERT", fecha, sessionn);
			generateAudit(updates, userName, client, IP, appName, "UPDATE", fecha, sessionn);
			generateAudit(deletes, userName, client, IP, appName, "DELETE", fecha, sessionn);

			sessionFactory.close();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			inserts.clear();
			updates.clear();
			deletes.clear();

		}

	}

	private void generateAudit(Set iterator, String userName, String client, String IP, String appName, String action,
			Timestamp fecha, Session session) {
		for (Iterator it = iterator.iterator(); it.hasNext();) {
			Object entity = it.next();
			Class<? extends Object> c = entity.getClass();
			String tableName = entity.getClass().getAnnotation(Table.class).name();
			JSONObject jsonObject = new JSONObject();

			for (Field field : c.getDeclaredFields()) {
				field.setAccessible(true);
				String name = field.getName();
				String value = "";
				try {
					value = String.valueOf(field.get(entity));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				jsonObject.put(name, value);
			}

			AudActividadAplicacion audit = new AudActividadAplicacion();
			audit.setAplUsuarioAplicacion(userName);
			audit.setAplAplicacion(appName);
			audit.setAplAplicacionCliente(client);
			audit.setAplFecha(fecha);
			audit.setAplIp(IP);
			audit.setAplOrigenArchivo(c.getName());
			audit.setAplSentencia(jsonObject.toString());
			audit.setAplTipoOperacion(action);
			audit.setAplTabla(tableName);
			
			session.persist(audit);
			session.flush();

		}
	}

}