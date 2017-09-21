package yxinfo.core.framework.mybatis.generator;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;

/**
 * Created by hanley on 2016/6/18.
 */
public class PaginationPlugin extends PluginAdapter {

    @Override
    public boolean modelExampleClassGenerated( TopLevelClass topLevelClass, IntrospectedTable introspectedTable ) {
        addPage( topLevelClass, introspectedTable, "page" );
        addOrClause( topLevelClass, introspectedTable, "addMutiOrClause" );
        return super.modelExampleClassGenerated( topLevelClass, introspectedTable );
    }

    private void addOrClause( TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name ) {

        InnerClass generatedCriteria = null;
        InnerClass criteria = null;
        InnerClass criterion = null;

        for ( InnerClass innerClass : topLevelClass.getInnerClasses() ) {

            if ( generatedCriteria != null && criteria != null && criterion != null ) break;

            if ( "Criterion".equals( innerClass.getType().getShortName() ) ) criterion = innerClass;
            if ( "Criteria".equals( innerClass.getType().getShortName() ) ) criteria = innerClass;
            if ( "GeneratedCriteria".equals( innerClass.getType().getShortName() ) ) generatedCriteria = innerClass;

        }

        if ( generatedCriteria != null && criteria != null && criterion != null ) {

            topLevelClass.addImportedType( "org.springframework.util.CollectionUtils" );

            Method method = new Method();

            // 遍历criteria查询条件
            method.setVisibility( JavaVisibility.PRIVATE );
            method.setName( "getKeyValue" );
            method.addParameter( new Parameter( criterion.getType(), "criterion" ) );
            method.addBodyLine( "StringBuffer sb = new StringBuffer();\n" +
                    "            if ( criterion.isNoValue() ) {\n" +
                    "                sb.append( criterion.getCondition() );\n" +
                    "            } else if ( criterion.isSingleValue() ) {\n" +
                    "                sb.append( criterion.getCondition() ).append( \" \" ).append( criterion.getValue() );\n" +
                    "            } else if ( criterion.betweenValue ) {\n" +
                    "                sb.append( criterion.getCondition() )\n" +
                    "                        .append( \" \" ).append( criterion.getValue() )\n" +
                    "                        .append( \" and \" ).append( criterion.getSecondValue() );\n" +
                    "            } else if ( criterion.listValue ) {\n" +
                    "                List<Object> inClause = (List<Object>) criterion.getValue();\n" +
                    "                if ( !CollectionUtils.isEmpty( inClause ) ) {\n" +
                    "                    sb.append( criterion.getCondition() ).append( \" ( \" );\n" +
                    "                    for ( int i = 0; i < inClause.size(); i++ ) {\n" +
                    "                        if ( i == inClause.size() - 1 ) {\n" +
                    "                            sb.append( inClause.get( i ) ).append( \" ) \" );\n" +
                    "                        } else {\n" +
                    "                            sb.append( inClause.get( i ) ).append( \" , \" );\n" +
                    "                        }\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            }\n" +
                    "            return sb.toString();" );
            method.setReturnType( new FullyQualifiedJavaType( "String" ) );
            generatedCriteria.addMethod( method );

            // 创建自定义方法
            method = new Method();
            method.setVisibility( JavaVisibility.PUBLIC );
            method.setName( name );
            method.addParameter( new Parameter( criteria.getType(), name ) );
            method.addBodyLine( "StringBuffer sb = new StringBuffer();\n" +
                    "            List<Criterion> orList = " + name + ".getCriteria();\n" +
                    "            if ( !CollectionUtils.isEmpty( orList ) ) {\n" +
                    "                sb.append( \"( \" );\n" +
                    "                for ( int i = 0; i< orList.size(); i++ ) {\n" +
                    "                    if ( i == " + name + ".getCriteria().size() - 1 ) {\n" +
                    "                        sb.append( getKeyValue( orList.get( i ) ) ).append( \" )\" );\n" +
                    "                    } else {\n" +
                    "                        sb.append( getKeyValue( orList.get( i ) ) ).append( \" or \" );\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            }\n" +
                    "            addCriterion( sb.toString() );\n" +
                    "            return (Criteria) this;" );
            method.setReturnType( new FullyQualifiedJavaType( "Criteria" ) );
            generatedCriteria.addMethod( method );
        }
    }

    private void addPage( TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name ) {

        topLevelClass.addImportedType( new FullyQualifiedJavaType( "yxinfo.core.framework.service.dal.Page" ) );
        topLevelClass.addImportedType( new FullyQualifiedJavaType( "yxinfo.core.framework.service.dal.dao.AbstractModel" ) );
        topLevelClass.setSuperClass( "AbstractModel" );

        CommentGenerator commentGenerator = context.getCommentGenerator();

        Field field = new Field();
        field.setVisibility( JavaVisibility.PROTECTED );
        field.setType( new FullyQualifiedJavaType( "yxinfo.core.framework.service.dal.Page" ) );
        field.setName( name );
        commentGenerator.addFieldComment( field, introspectedTable );
        topLevelClass.addField( field );
        char c = name.charAt( 0 );
        String camel = Character.toUpperCase( c ) + name.substring( 1 );
        Method method = new Method();
        method.setVisibility( JavaVisibility.PUBLIC );
        method.setName( "set" + camel );
        method.addParameter( new Parameter( new FullyQualifiedJavaType( "yxinfo.core.framework.service.dal.Page" ), name ) );
        method.addBodyLine( "this." + name + "=" + name + ";" );
        commentGenerator.addGeneralMethodComment( method, introspectedTable );
        topLevelClass.addMethod( method );
        method = new Method();
        method.setVisibility( JavaVisibility.PUBLIC );
        method.setReturnType( new FullyQualifiedJavaType( "yxinfo.core.framework.service.dal.Page" ) );
        method.setName( "get" + camel );
        method.addBodyLine( "return " + name + ";" );
        commentGenerator.addGeneralMethodComment( method, introspectedTable );
        topLevelClass.addMethod( method );
    }

    public boolean validate( List<String> list ) {
        return true;
    }
}
