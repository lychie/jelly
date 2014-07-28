package org.jelly.support;

import java.sql.Types;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;
/**
 * MySql Chinese Order Support
 * @author Lychie Fan
 * @since 1.2.0
 */
public class MySqlChineseOrderSupport extends Order {

    private boolean ascending;
	private boolean ignoreCase;
    private String propertyName;
	private static final String ENCODING = "GBK";
	private static final long serialVersionUID = 1L;
	
	protected MySqlChineseOrderSupport(String propertyName, boolean ascending) {
		super(propertyName, ascending);
		this.propertyName = propertyName;
		this.ascending = ascending;
	}
	
	public static Order asc(String propertyName){
		return new MySqlChineseOrderSupport(propertyName, true);
	}
	
	public static Order desc(String propertyName) {
		return new MySqlChineseOrderSupport(propertyName, false);
	}

	@Override
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
		String[] columns = criteriaQuery.getColumnsUsingProjection(criteria, propertyName);
        Type type = criteriaQuery.getTypeUsingProjection(criteria, propertyName);
        StringBuffer fragment = new StringBuffer();
        for (int i = 0; i < columns.length; i++) {
            SessionFactoryImplementor factory = criteriaQuery.getFactory();
            boolean lower = ignoreCase && type.sqlTypes(factory)[i] == Types.VARCHAR;
            if (lower) {
                fragment.append(factory.getDialect().getLowercaseFunction()).append('(');
            }
            fragment.append("CONVERT( ").append(columns[i]).append(" USING ").append(ENCODING).append(" )");
            if (lower)
                fragment.append(')');
            fragment.append(ascending ? " asc" : " desc");
            if (i < columns.length - 1)
                fragment.append(", ");
        }
        return fragment.toString();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("CONVERT( ").append(propertyName).append(" USING ");
		buffer.append(ENCODING).append(" ) ").append(ascending ? "asc" : "desc");
		return buffer.toString();
	}

}