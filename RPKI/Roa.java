import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Roa {

	@SerializedName("prefix")
	@Expose
	private String prefix;
	@SerializedName("maxLength")
	@Expose
	private Integer maxLength;
	@SerializedName("asn")
	@Expose
	private String asn;
	@SerializedName("ta")
	@Expose
	private String ta;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public String getAsn() {
		return asn;
	}

	public void setAsn(String asn) {
		this.asn = asn;
	}

	public String getTa() {
		return ta;
	}

	public void setTa(String ta) {
		this.ta = ta;
	}

}