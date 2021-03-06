package si.telekom.dis.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>AdminService</code>.
 */
public interface AdminServiceAsync {
	void getActions(String loginName, String password, AsyncCallback<List<Action>> callback) throws IllegalArgumentException;

	void getActionById(String actionId, AsyncCallback<Action> callback) throws IllegalArgumentException;

	void getDocTypes(String loginName, String password, AsyncCallback<List<DocType>> callback);

	void getDocType(String loginName, String password, String doctypeName, AsyncCallback<DocType> callback);

	void getProfiles(String loginName, String password, AsyncCallback<List<Profile>> callback);

	void getProfile(String loginName, String password, String profileId, AsyncCallback<Profile> callback);

	void setProfile(String loginName, String password, Profile prof, AsyncCallback<String> callback) throws IllegalArgumentException;

	void deleteProfile(String loginName, String password, String profileId, AsyncCallback<Void> callback) throws IllegalArgumentException;

	void getProfilesForClassSign(String loginName, String password, String classSign, String wizardType, AsyncCallback<List<Profile>> callback)
			throws IllegalArgumentException;

	void syncDoctypes(String loginName, String password, AsyncCallback<Void> callback) throws IllegalArgumentException;

	void registerTable(String loginName, String password, String tableName, AsyncCallback<Void> callback) throws ServerException;

	void getRegTableValues(String loginName, String loginPass, String regTableId, String filters, int start, int length, 
			AsyncCallback<List<List<String>>> asyncCallback) throws ServerException;

	void getRegTables(String loginName, String loginPass, AsyncCallback<List<String>> asyncCallback) throws ServerException;

	void updateRegTableRow(String loginName, String loginPass, String regTableId, String value, int columnIndex, String pkValue,
			AsyncCallback<Void> asyncCallback);

	void getRegTableFieldNames(String loginName, String loginPass, String regTableName, AsyncCallback<List<String>> asyncCallback)
			throws ServerException;

	void getSearchQueries(String loginName, String dcmtUserName, String loginPass, AsyncCallback<List<MyParametrizedQuery>> asyncCallback)
			throws ServerException;

	void getLazySearchQueries(String loginName, String loginPass, String dctmUserName, AsyncCallback<List<MyParametrizedQuery>> asyncCallback)
			throws ServerException;

	void getParametrizedQueryByName(String loginName, String loginPass, String name, AsyncCallback<MyParametrizedQuery> asyncCallback)
			throws ServerException;

	void editParametrizedQuery(String loginName, String loginPass, String oldName, String newName, String newDql, List<String> groups,
			List<String> orderBy, List<String> orderByDirections, String filterClass, List<String> labels, AsyncCallback<Void> callback) throws ServerException;

	void duplicateParametrizedQuery(String loginName, String loginPass, String oldName, String name, String query, AsyncCallback<String> asyncCallback) throws ServerException;

	void deleteParametrizedQuery(String loginName, String loginPass, String name, AsyncCallback<String> asyncCallback) throws ServerException;	
	
	void getDocTypeFromDql(String loginName, String loginPass, String dql, AsyncCallback<DocType> asyncCallBack) throws ServerException;

	void getColIdsForTemplate(int templateId, int start, int length, AsyncCallback<List<List<String>>> asyncCallBack) throws ServerException;

	void updateCollId(String loginName, String loginPass, int templateId, String templateName, String col_id, String columnName, String value,
			AsyncCallback<Void> callback);

	void deleteCollId(String loginName, String loginPass, int templateId, String col_id, AsyncCallback<Void> callback) throws ServerException;

	void transferToAllEnvironments(String loginName, String loginPass, int templateId, AsyncCallback<Void> callback) throws ServerException;
	
	void getRegTableFieldTypes(String tableName, AsyncCallback<List<String>> callback) throws ServerException;
	
	void runDql(String loginName, String loginPassword, String dql, AsyncCallback<Void> callback) throws ServerException;
	
	void insertTSVToRegTable(String loginName, String loginPass, String regTableId, String csvValues, AsyncCallback<Void> callback) throws ServerException;
	
	void updateParameterLabelForParametrizedQuery(String loginName, String loginPass, String name, List<String> parameterLabels, AsyncCallback<Void> asyncCallback) throws ServerException;

	void parseProfileFromXml(String loginName, String xml, AsyncCallback<Void> asyncCallback) throws ServerException;
}
