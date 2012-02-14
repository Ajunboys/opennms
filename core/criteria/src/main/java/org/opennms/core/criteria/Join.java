package org.opennms.core.criteria;

public class Join {
	public enum JoinType { LEFT_JOIN, RIGHT_JOIN, FULL_JOIN }

	private final String m_associationPath;
	private final String m_alias;
	private final JoinType m_type;

	public Join(final String associationPath, final String alias, final JoinType type) {
		m_alias = alias.intern();
		m_associationPath = associationPath.intern();
		m_type = type;
	}
	
	public String getAlias() {
		return m_alias;
	}
	
	public String getAssociationPath() {
		return m_associationPath;
	}

	public JoinType getType() {
		return m_type;
	}

}

