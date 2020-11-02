package si.telekom.dis.shared;

public class ExtendedPermit {

	/*
	 * Presentation of Extended permissions Extended permissions are a feature
	 * only available in version 4i and later. They greatly enhance the security
	 * capabilities of the server by letting certain users access admin functions
	 * on a per-document basis. For example, in pre-4i docbases, only two types of
	 * users could change the permissions on a document: the owner of that
	 * document and a superuser. In 4i, you can use the extended permissions to
	 * allow certain normal users to change the permissions. For example, an ACL
	 * for the Marketing department might allow the marketing_managers group to
	 * change the permissions on the document.
	 * 
	 * The extended permissions are described below:
	 * 
	 * execute_proc: Allows the user to execute the procedure (if it is a
	 * procedure) change_location: Allows the user to change the location of the
	 * document. change_state: Allows the user to change the state of the document
	 * using the document lifecycle. change_permit: Allows the user to change the
	 * object’s permissions. change_owner: Allows the user to change the owner of
	 * the object. delete_object: Delete permission. Delete Object extended
	 * permission does not grant Browse, Read, Relate, Version, or Write
	 * permission. change_folder_links: Allows the user to create a document in a
	 * folder without having the write right on this folder.
	 * 
	 * More the Extended permissions are stored in the r_accessor_xpermit
	 * attribute of an dm_ACL. The value of this attribute is an integer that has
	 * been converted from a 4-bytes binary number (or 32-bits binary number) to a
	 * decimal number. Each permission is governed by the value of a bit in a
	 * particular place: 1 : signifies the permission is granted, 0 : signifies it
	 * is not. Warning : For some reason, execute_proc and change_location are
	 * reversed, “1” signifies the permission is not granted and “0” signifies it
	 * is granted.
	 * 
	 * The bit locations of the extended permissions are defined like this (from
	 * right):
	 * 
	 * bit 01 : execute_proc 
	 * bit 02 : change_location 
	 * bit 17 : change_state 
	 * bit 18 : change_permissions 
	 * bit 19 : change_owner 
	 * bit 20 : extended_delete 
	 * bit 21 : change_folder_links
	 */

	public enum extPermit implements com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable {
	//@formatter:off
		
//    if (this.hasExecuteProc()) {
//      result.add("EXECUTE_PROC");
//  }
//  if (this.hasChangeLocation()) {
//      result.add("CHANGE_LOCATION");
//  }
//  if (this.hasChangeState()) {
//      result.add("CHANGE_STATE");
//  }
//  if (this.hasChangePermit()) {
//      result.add("CHANGE_PERMIT");
//  }
//  if (this.hasChangeOwner()) {
//      result.add("CHANGE_OWNER");
//  }
//  if (this.hasDeleteObject()) {
//      result.add("DELETE_OBJECT");
//  }
//  if (this.hasChangeFolderLinks()) {
//      result.add("CHANGE_FOLDER_LINKS");		
		
		
			CHANGE_OWNER("CHANGE_OWNER"),
			CHANGE_STATE("CHANGE_STATE"),
			CHANGE_PERMIT("CHANGE_PERMIT"),
			CHANGE_FOLDER_LINKS("CHANGE_FOLDER_LINKS"),
			EXECUTE_PROC("EXECUTE_PROC"),
			CHANGE_LOCATION("CHANGE_LOCATION");
		//@formatter:on

		public String type;

		public String value() {
			return this.type;
		}

		private extPermit(String type_) {
			this.type = type_;
		}
	}
}
