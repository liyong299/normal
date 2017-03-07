/*     */ package org.apache.ibatis.mapping;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
import java.util.Map;

/*     */ import org.apache.ibatis.cache.Cache;
/*     */ import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
/*     */ import org.apache.ibatis.executor.keygen.KeyGenerator;
/*     */ import org.apache.ibatis.executor.keygen.NoKeyGenerator;
/*     */ import org.apache.ibatis.logging.Log;
/*     */ import org.apache.ibatis.logging.LogFactory;
/*     */ import org.apache.ibatis.scripting.LanguageDriver;
/*     */ import org.apache.ibatis.session.Configuration;
/*     */ 
/*     */ public final class MappedStatement
/*     */ {
/*     */   private String resource;
/*     */   private Configuration configuration;
/*     */   private String id;
/*     */   private Integer fetchSize;
/*     */   private Integer timeout;
/*     */   private StatementType statementType;
/*     */   private ResultSetType resultSetType;
/*     */   private SqlSource sqlSource;
/*     */   private Cache cache;
/*     */   private ParameterMap parameterMap;
/*     */   private List<ResultMap> resultMaps;
/*     */   private boolean flushCacheRequired;
/*     */   private boolean useCache;
/*     */   private boolean resultOrdered;
/*     */   private SqlCommandType sqlCommandType;
/*     */   private KeyGenerator keyGenerator;
/*     */   private String[] keyProperties;
/*     */   private String[] keyColumns;
/*     */   private boolean hasNestedResultMaps;
/*     */   private String databaseId;
/*     */   private Log statementLog;
/*     */   private LanguageDriver lang;
/*     */   private String[] resultSets;
/*     */ 
/*     */   public KeyGenerator getKeyGenerator()
/*     */   {
/* 187 */     return this.keyGenerator;
/*     */   }
/*     */ 
/*     */   public SqlCommandType getSqlCommandType() {
/* 191 */     return this.sqlCommandType;
/*     */   }
/*     */ 
/*     */   public String getResource() {
/* 195 */     return this.resource;
/*     */   }
/*     */ 
/*     */   public Configuration getConfiguration() {
/* 199 */     return this.configuration;
/*     */   }
/*     */ 
/*     */   public String getId() {
/* 203 */     return this.id;
/*     */   }
/*     */ 
/*     */   public boolean hasNestedResultMaps() {
/* 207 */     return this.hasNestedResultMaps;
/*     */   }
/*     */ 
/*     */   public Integer getFetchSize() {
/* 211 */     return this.fetchSize;
/*     */   }
/*     */ 
/*     */   public Integer getTimeout() {
/* 215 */     return this.timeout;
/*     */   }
/*     */ 
/*     */   public StatementType getStatementType() {
/* 219 */     return this.statementType;
/*     */   }
/*     */ 
/*     */   public ResultSetType getResultSetType() {
/* 223 */     return this.resultSetType;
/*     */   }
/*     */ 
/*     */   public SqlSource getSqlSource() {
/* 227 */     return this.sqlSource;
/*     */   }
/*     */ 
/*     */   public ParameterMap getParameterMap() {
/* 231 */     return this.parameterMap;
/*     */   }
/*     */ 
/*     */   public List<ResultMap> getResultMaps() {
/* 235 */     return this.resultMaps;
/*     */   }
/*     */ 
/*     */   public Cache getCache() {
/* 239 */     return this.cache;
/*     */   }
/*     */ 
/*     */   public boolean isFlushCacheRequired() {
/* 243 */     return this.flushCacheRequired;
/*     */   }
/*     */ 
/*     */   public boolean isUseCache() {
/* 247 */     return this.useCache;
/*     */   }
/*     */ 
/*     */   public boolean isResultOrdered() {
/* 251 */     return this.resultOrdered;
/*     */   }
/*     */ 
/*     */   public String getDatabaseId() {
/* 255 */     return this.databaseId;
/*     */   }
/*     */ 
/*     */   public String[] getKeyProperties() {
/* 259 */     return this.keyProperties;
/*     */   }
/*     */ 
/*     */   public String[] getKeyColumns() {
/* 263 */     return this.keyColumns;
/*     */   }
/*     */ 
/*     */   public Log getStatementLog() {
/* 267 */     return this.statementLog;
/*     */   }
/*     */ 
/*     */   public LanguageDriver getLang() {
/* 271 */     return this.lang;
/*     */   }
/*     */ 
/*     */   public String[] getResulSets() {
/* 275 */     return this.resultSets;
/*     */   }
/*     */ 
/*     */   public BoundSql getBoundSql(Object parameterObject) {
		if (parameterObject instanceof Map) {
			Map map = (Map) parameterObject;
			map.put("tenant", "ZY");
		}
/* 279 */     BoundSql boundSql = this.sqlSource.getBoundSql(parameterObject);
/* 280 */     List parameterMappings = boundSql.getParameterMappings();
/* 281 */     if ((parameterMappings == null) || (parameterMappings.isEmpty())) {
/* 282 */       boundSql = new BoundSql(this.configuration, boundSql.getSql(), this.parameterMap.getParameterMappings(), parameterObject);
/*     */     }
/*     */ 
/* 286 */     for (ParameterMapping pm : boundSql.getParameterMappings()) {
/* 287 */       String rmId = pm.getResultMapId();
/* 288 */       if (rmId != null) {
/* 289 */         ResultMap rm = this.configuration.getResultMap(rmId);
/* 290 */         if (rm != null) {
/* 291 */           this.hasNestedResultMaps |= rm.hasNestedResultMaps();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 296 */     return boundSql;
/*     */   }
/*     */ 
/*     */   private static String[] delimitedStringtoArray(String in) {
/* 300 */     if ((in == null) || (in.trim().length() == 0)) {
/* 301 */       return null;
/*     */     }
/* 303 */     return in.split(",");
/*     */   }
/*     */ 
/*     */   public static class Builder
/*     */   {
/*  65 */     private MappedStatement mappedStatement = new MappedStatement();
/*     */ 
/*     */     public Builder(Configuration configuration, String id, SqlSource sqlSource, SqlCommandType sqlCommandType) {
/*  68 */       this.mappedStatement.configuration = configuration;
/*  69 */       this.mappedStatement.id = id;
/*  70 */       this.mappedStatement.sqlSource = sqlSource;
/*  71 */       this.mappedStatement.statementType = StatementType.PREPARED;
/*  72 */       this.mappedStatement.parameterMap = new ParameterMap.Builder(configuration, "defaultParameterMap", null, new ArrayList()).build();
/*  73 */       this.mappedStatement.resultMaps = new ArrayList();
/*  74 */       this.mappedStatement.sqlCommandType = sqlCommandType;
/*  75 */       this.mappedStatement.keyGenerator = ((configuration.isUseGeneratedKeys()) && (SqlCommandType.INSERT.equals(sqlCommandType)) ? new Jdbc3KeyGenerator() : new NoKeyGenerator());
/*  76 */       String logId = id;
/*  77 */       if (configuration.getLogPrefix() != null) {
/*  78 */         logId = configuration.getLogPrefix() + id;
/*     */       }
/*  80 */       this.mappedStatement.statementLog = LogFactory.getLog(logId);
/*  81 */       this.mappedStatement.lang = configuration.getDefaultScriptingLanuageInstance();
/*     */     }
/*     */ 
/*     */     public Builder resource(String resource) {
/*  85 */       this.mappedStatement.resource = resource;
/*  86 */       return this;
/*     */     }
/*     */ 
/*     */     public String id() {
/*  90 */       return this.mappedStatement.id;
/*     */     }
/*     */ 
/*     */     public Builder parameterMap(ParameterMap parameterMap) {
/*  94 */       this.mappedStatement.parameterMap = parameterMap;
/*  95 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder resultMaps(List<ResultMap> resultMaps) {
/*  99 */       this.mappedStatement.resultMaps = resultMaps;
/* 100 */       for (ResultMap resultMap : resultMaps) {
/* 101 */         this.mappedStatement.hasNestedResultMaps = ((this.mappedStatement.hasNestedResultMaps) || (resultMap.hasNestedResultMaps()));
/*     */       }
/* 103 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder fetchSize(Integer fetchSize) {
/* 107 */       this.mappedStatement.fetchSize = fetchSize;
/* 108 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder timeout(Integer timeout) {
/* 112 */       this.mappedStatement.timeout = timeout;
/* 113 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder statementType(StatementType statementType) {
/* 117 */       this.mappedStatement.statementType = statementType;
/* 118 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder resultSetType(ResultSetType resultSetType) {
/* 122 */       this.mappedStatement.resultSetType = resultSetType;
/* 123 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder cache(Cache cache) {
/* 127 */       this.mappedStatement.cache = cache;
/* 128 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder flushCacheRequired(boolean flushCacheRequired) {
/* 132 */       this.mappedStatement.flushCacheRequired = flushCacheRequired;
/* 133 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder useCache(boolean useCache) {
/* 137 */       this.mappedStatement.useCache = useCache;
/* 138 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder resultOrdered(boolean resultOrdered) {
/* 142 */       this.mappedStatement.resultOrdered = resultOrdered;
/* 143 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder keyGenerator(KeyGenerator keyGenerator) {
/* 147 */       this.mappedStatement.keyGenerator = keyGenerator;
/* 148 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder keyProperty(String keyProperty) {
/* 152 */       this.mappedStatement.keyProperties = MappedStatement.delimitedStringtoArray(keyProperty);
/* 153 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder keyColumn(String keyColumn) {
/* 157 */       this.mappedStatement.keyColumns = MappedStatement.delimitedStringtoArray(keyColumn);
/* 158 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder databaseId(String databaseId) {
/* 162 */       this.mappedStatement.databaseId = databaseId;
/* 163 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder lang(LanguageDriver driver) {
/* 167 */       this.mappedStatement.lang = driver;
/* 168 */       return this;
/*     */     }
/*     */ 
/*     */     public Builder resulSets(String resultSet) {
/* 172 */       this.mappedStatement.resultSets = MappedStatement.delimitedStringtoArray(resultSet);
/* 173 */       return this;
/*     */     }
/*     */ 
/*     */     public MappedStatement build() {
/* 177 */       assert (this.mappedStatement.configuration != null);
/* 178 */       assert (this.mappedStatement.id != null);
/* 179 */       assert (this.mappedStatement.sqlSource != null);
/* 180 */       assert (this.mappedStatement.lang != null);
/* 181 */       this.mappedStatement.resultMaps = Collections.unmodifiableList(this.mappedStatement.resultMaps);
/* 182 */       return this.mappedStatement;
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\home\mopon\.m2\repository\org\mybatis\mybatis\3.3.1\mybatis-3.3.1.jar
 * Qualified Name:     org.apache.ibatis.mapping.MappedStatement
 * JD-Core Version:    0.6.2
 */