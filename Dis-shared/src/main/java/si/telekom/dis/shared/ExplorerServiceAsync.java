package si.telekom.dis.shared;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>LoginService</code>.
 */
public interface ExplorerServiceAsync {
	void getObjects(String folder, boolean  current, String loginName, String password, int start, int length, AsyncCallback<List<Document>> asyncCallback) throws IllegalArgumentException;
	void getActionsForObject(String loginName, String password, String r_object_id, AsyncCallback<List<Action>> callback) throws IllegalArgumentException;
	void getRenditions(String loginName, String password, String r_object_id, AsyncCallback<List<String>> callback) throws IllegalArgumentException;
	void getProfileAttributesAndValues(String loginName, String password, String r_object_id, AsyncCallback<ProfileAttributesAndValues> callback) throws IllegalArgumentException;
	void getValuesFromDql(String loginName, String password, String likeString, String dql, AsyncCallback<List<String[]>> callback) throws IllegalArgumentException;
	void getValuesFromSql(String loginName, String password, String likeString, String dql, AsyncCallback<List<String[]>> callback) throws IllegalArgumentException;
	void getValuesFromRest(String loginName, String password, String likeString, String restUrl, AsyncCallback<List<String[]>> callback) throws IllegalArgumentException;
	
	void getDefaultValueDql(String dql, AsyncCallback<List<List<String>>> callback) throws IllegalArgumentException;
	
	
	void setAttributes(String loginName, String password, String r_object_id, List<String[]> values, AsyncCallback<Void> callback) throws IllegalArgumentException;
	void deleteObject(String loginName, String password, String r_object_id, boolean allVersions, AsyncCallback<Void> callback) throws IllegalArgumentException;
	void deleteObjects(String loginName, String loginPass, List<String> allChecked, boolean allV, AsyncCallback<Void> asyncCallback);
	void dqlLookup(String loginName, String password, String dql, AsyncCallback<List<List<String>>> callback) throws IllegalArgumentException;
	void setUsersForRoles(String loginName, String password, String r_object_id, Map<String,List<String>> rolesUsers, AsyncCallback<Void> callback) throws IllegalArgumentException;
	void checkout(String loginName, String password, String r_object_id, AsyncCallback<Void> callback) throws IllegalArgumentException;
	void cancelCheckout(String loginName, String password, String r_object_id, AsyncCallback<Void> callback) throws IllegalArgumentException;
	void unlock(String loginName, String password, String r_object_id, AsyncCallback<Void> callback) throws IllegalArgumentException;

	void versions(String loginName, String password, String r_object_id, AsyncCallback<List<Document>> callback) throws IllegalArgumentException;
	void promote(String loginName, String password, String r_object_id, AsyncCallback<Void> callback) throws IllegalArgumentException;
	void demote(String loginName, String password, String r_object_id, AsyncCallback<Void> callback) throws IllegalArgumentException;

	
	void auditTrail(String loginName, String password, String r_object_id, int start, int length, AsyncCallback<List<List<String>>> callback) throws IllegalArgumentException;
	void newFolder(String loginName, String password, String folderName, String pareFolderPath, AsyncCallback<Void> callback) throws IllegalArgumentException;
	void addVersionLabel(String loginName, String loginPass, String r_object_id_, String labelVersion, AsyncCallback<Void> asyncCallback) throws IllegalArgumentException;
	void removeVersionLabel(String loginName, String loginPass, String r_object_id_, String labelVersion, AsyncCallback<Void> asyncCallback) throws IllegalArgumentException;
	
	void newDocument(String loginName, String password, String profileId, Map<String,List<String>> attributes, Map<String,List<String>> rolesUsers, String templateObjectNameOrFolder, AsyncCallback<String> callback) throws IllegalArgumentException;
	void importDocument(String loginName, String password, String objectId, String profileId, Map<String,List<String>> attributes, Map<String,List<String>> rolesUsers, byte[] base64Content, String format, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void runSearchQuery(String loginName, String password, String dql, MyParametrizedQuery pQuery, int start, int length, AsyncCallback<List<Document>> asyncCallback);
	
	void showPdfTags(String loginName, String loginPassword, String r_object_id, AsyncCallback<List<List<String>>> asyncCallback);
	
	void updateBusinessNotification(String loginName, String password, String r_object_id, AsyncCallback<Void> asyncCallback);
	
	void newRelease(String loginName, String password, String r_object_id, AsyncCallback<Void> asyncCallback);
	
	void recognizeFormat(String base64Content, AsyncCallback<List<String>> asyncCallback);
	
	void removeRendition(String loginName, String password, String r_object_id, String format, AsyncCallback<Void> asyncCallback);

	void classifyDocument(String loginName, String password, String r_object_id, String profileId, String stateId, Map<String,List<String>> attributes, Map<String,List<String>> rolesUsers, AsyncCallback<Void> callback);
	
	void massDownloadContent(String loginName, String password, List<String> r_object_ids, AsyncCallback<String> callback);

	void prepareAiTrainDataForFolderAndClassification(String loginName, String password, List<String> r_object_ids, String classification, boolean deleteTrainingDir, AsyncCallback<Void> callback);
	
}
