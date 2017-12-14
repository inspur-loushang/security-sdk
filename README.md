# security-sdk
[TOC]
## 1. 前提
首先完成keycloak的配置，能够通过keycloak进行认证
## 2. pom依赖
在pom中添加security-sdk以及keycloak依赖
```
<!-- security-sdk -->
<dependency>
	<groupId>com.inspur.security</groupId>
	<artifactId>security-sdk</artifactId>
	<version>1.0.1-alpha</version>
</dependency>
<!-- keycloak -->
<dependency>
	<groupId>org.keycloak</groupId>
	<artifactId>keycloak-core</artifactId>
	<version>3.4.0.Final</version>
	<scope>provided</scope>
</dependency>
<dependency>
	<groupId>org.keycloak</groupId>
	<artifactId>keycloak-adapter-core</artifactId>
	<version>3.4.0.Final</version>
	<scope>provided</scope>
</dependency>
<dependency>
	<groupId>org.keycloak</groupId>
	<artifactId>keycloak-adapter-spi</artifactId>
	<version>3.4.0.Final</version>
	<scope>provided</scope>
</dependency>
```
## 3. web.xml配置
在web.xml中添加security-sdk过滤器
```
<!-- security-sdk过滤器 -->
<filter>
	<filter-name>SDKFilter</filter-name>
	<filter-class>
		sdk.security.filter.SDKFilter
	</filter-class>
	<init-param>
		<param-name>authzFilter</param-name>
		<param-value>false</param-value>
	</init-param>
</filter>
<!-- security-sdk过滤器 -->
<filter-mapping>
	<filter-name>SDKFilter</filter-name>
	<url-pattern>/service/*</url-pattern>
</filter-mapping>
```
## 4. 配置文件
- securitySDK.properties

securitySDK配置文件，用于配置接口的实现类，存在以下默认值，该文件可以默认可以省略
```
#配置认证接口实现类
securitySDK.AuthenticationProviderImpl=sdk.security.service.impl.AuthenticationProviderImpl
#配置权限接口实现类
securitySDK.AuthorizationProviderImpl=sdk.security.service.impl.AuthorizationProviderImpl
#配置用户接口实现类
securitySDK.UserProviderImpl=sdk.security.service.impl.UserProviderImpl
```
- securitySDKAuthz.json
securitySDK权限配置文件，当调用权限相关接口时需要配置该文件
示例：
```json
[
    {
        "roleId": "superadmin", 
        "resources": [
            {
                "rid": "m_dashbord", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_service", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_warn", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_log", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_env", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_env_host", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_env_secret", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_env_monitor", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_env_app", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_env_bigData", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_tenant", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_tenant_resource", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_tenant_resource_hdfs", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_tenant_resource_hbase", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_tenant_resource_hive", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_tenant_resource_kafka", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_tenant_resource_yarn", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_tenant_authz", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_tenant_authz_hdfs", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_tenant_authz_hbase", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_tenant_authz_hive", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_tenant_authz_kafka", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_tenant_authz_yarn", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_security", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_security_user", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_security_user_manage", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_security_audit", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_security_audit_access", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_security_audit_manage", 
                "url": "", 
                "type": "menu"
            }, 
            {
                "rid": "m_resource", 
                "url": "/*", 
                "type": "resource"
            }
        ]
    }, 
    {
        "roleId": "user", 
        "resources": [
            {
                "rid": "user_resource", 
                "url": "/*", 
                "type": "resource"
            }
        ]
    }
]
```
- securitySDKMenu.json
securitySDK菜单配置文件，调用获取菜单接口时需要配置该文件，对菜单进行权限过滤时应该同时配置securitySDKAuthz.json文件，并且securitySDKMenu.json文件中的**id**应与securitySDKAuthz.json文件中的**rid**相对应
示例：
```json
{
  "menu": {
    "rows": [
	 {
        "id": "m_dashbord",
        "text": "仪表盘",
        "url": "/indata-manage-portal/jsp/portal/main.jsp",
        "icon": "",
        "isLeaf": "true"
      },
	  {
        "id": "m_service",
        "text": "服务管理",
        "url": "http://idap-1.idap.com:8080/#/main/services/HDFS/summary",
        "icon": "",
        "isLeaf": "true"
      },
	  {
        "id": "m_warn",
        "text": "告警",
        "url": "http://idap-1.idap.com:8080/#/main/alerts",
        "icon": "",
        "isLeaf": "true"
      },
	  {
        "id": "m_log",
        "text": "日志",
        "url": "http://10.110.18.59:61888/",
        "icon": "",
        "isLeaf": "true"
      },
      {
        "id": "m_env",
        "text": "基础环境管理",
        "url": "",
        "icon": "",
        "isLeaf": "false",
		"description": "",
        "children": [
		  {
			"id": "m_env_host",
			"text": "主机管理",
			"url": "/manage-cluster/service/host/index",
			"icon": "",
			"isLeaf": "true"
		  },
		  {
			"id": "m_env_secret",
			"text": "安全集群",
			"url": "/indata-dev-portal/jsp/test/hbase.jsp",
			"icon": "",
			"isLeaf": "true"
		  },
		  {
			"id": "m_env_monitor",
			"text": "监控集群",
			"url": "",
			"icon": "",
			"isLeaf": "true"
		  },
		  {
			"id": "m_env_app",
			"text": "应用集群",
			"url": "",
			"icon": "",
			"isLeaf": "true"
		  },
		  {
			"id": "m_env_bigData",
			"text": "大数据管理集群",
			"url": "",
			"icon": "",
			"isLeaf": "true"
		  }
		]
      },
	  {
        "id": "m_tenant",
        "text": "多租户管理",
        "url": "",
        "icon": "",
        "isLeaf": "false",
		"description": "",
        "children": [
          {
            "id": "m_tenant_resource",
            "text": "资源配额管理",
            "url": "",
            "icon": "",
            "isLeaf": "false",
            "children": [
              {
                "id": "m_tenant_resource_hdfs",
                "text": "HDFS资源配额管理",
                "url": "/manage-store/service/hdfs",
                "icon": "menu",
                "isLeaf": "true"
              },
              {
                "id": "m_tenant_resource_hbase",
                "text": "HBase资源配额管理",
                "url": "/manage-store/service/hbase/HBaseResourceController",
                "icon": "menu",
                "isLeaf": "true"
              },
			  {
                "id": "m_tenant_resource_hive",
                "text": "Hive资源配额管理",
                "url": "/manage-store/service/hive/getSuperAdminPage",
                "icon": "menu",
                "isLeaf": "true"
              },
			  {
                "id": "m_tenant_resource_kafka",
                "text": "Kafka资源配额管理",
                "url": "/manage-dataflow/service/kafka/quota/getPage",
                "icon": "menu",
                "isLeaf": "true"
              },
			  {
                "id": "m_tenant_resource_yarn",
                "text": "Yarn资源配额管理",
                "url": "/manage-store/service/manage/yarn/resource/queue",
                "icon": "menu",
                "isLeaf": "true"
              }
            ]
          },
          {
            "id": "m_tenant_authz",
            "text": "资源授权管理",
            "url": "",
            "icon": "",
            "isLeaf": "false",
            "children": [
              {
                "id": "m_tenant_authz_hdfs",
                "text": "HDFS资源授权管理",
                "url": "/manage-store/service/auth/policy",
                "icon": "menu",
                "isLeaf": "true"
              },
              {
                "id": "m_tenant_authz_hbase",
                "text": "HBase资源授权管理",
                "url": "/manage-store/service/hbase/HBaseAuthorizationController",
                "icon": "menu",
                "isLeaf": "true"
              },
			  {
                "id": "m_tenant_authz_hive",
                "text": "Hive资源授权管理",
                "url": "/manage-store/service/hive/authorization/getSupPage",
                "icon": "menu",
                "isLeaf": "true"
              },
			  {
                "id": "m_tenant_authz_kafka",
                "text": "Kafka资源授权管理",
                "url": "/manage-dataflow/service/kafka/authorization/getPage",
                "icon": "menu",
                "isLeaf": "true"
              },
			  {
                "id": "m_tenant_authz_yarn",
                "text": "Yarn资源授权管理",
                "url": "/manage-store/service/manage/yarn/authorization/policy",
                "icon": "menu",
                "isLeaf": "true"
              }
            ]
          }
        ]
      },
      {
        "id": "m_security",
        "text": "安全管理",
        "url": "",
        "icon": "",
        "isLeaf": "false",
		"description": "",
        "children": [
          {
            "id": "m_security_user",
            "text": "用户管理",
            "url": "",
            "icon": "",
            "isLeaf": "false",
            "children": [
              {
                "id": "m_security_user_manage",
                "text": "用户管理",
                "url": "/indata-manage-portal/service/manage/security/user",
                "icon": "user",
                "isLeaf": "true"
              }
            ]
          },
          {
            "id": "m_security_audit",
            "text": "审计管理",
            "url": "",
            "icon": "audit",
            "isLeaf": "false",
			"children": [
              {
                "id": "m_security_audit_access",
                "text": "访问日志",
                "url": "/indata-manage-portal/service/manage/security/audit/access",
                "icon": "menu",
                "isLeaf": "true"
              },
			  {
                "id": "m_security_audit_manage",
                "text": "管理日志",
                "url": "/indata-manage-portal/service/manage/security/audit/admin",
                "icon": "menu",
                "isLeaf": "true"
              }
            ]
          }
        ]
      }     
    ]
  }
}
```