package com.collin.joyous.web.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

public class JoyousSessionDAO extends AbstractSessionDAO {

	private Map<Serializable,Session> sessionMap = new HashMap<Serializable,Session>();
	@Override
	public void delete(Session session) {
		sessionMap.remove(session.getId());

	}

	@Override
	public Collection<Session> getActiveSessions() {
		return sessionMap.values();
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		sessionMap.put(session.getId(), session);

	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		sessionMap.put(sessionId, session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		return sessionMap.get(sessionId);
	}

}
