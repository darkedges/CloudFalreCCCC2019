import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RPKI {

	@SerializedName("metadata")
	@Expose
	private Metadata metadata;
	@SerializedName("roas")
	@Expose
	private List<Roa> roas = null;

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<Roa> getRoas() {
		return roas;
	}

	public void setRoas(List<Roa> roas) {
		this.roas = roas;
	}

}
