package si.telekom.dis.shared;

public class Permit {
	public enum permit implements com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable {
	//@formatter:off
			NONE(1),
			BROWSE(2),
			READ(3),
			RELATE(4),
			VERSION(5),
			WRITE(6),
			DELETE(7);
		//@formatter:on

			public int type;
			
			public int value() {
				return this.type;
			}

			private permit(int type_) {
				this.type = type_;
			}
		}
}
