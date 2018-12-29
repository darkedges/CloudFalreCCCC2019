

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

	@SerializedName("counts")
	@Expose
	private Integer counts;
	@SerializedName("generated")
	@Expose
	private Integer generated;
	@SerializedName("valid")
	@Expose
	private Integer valid;
	@SerializedName("signature")
	@Expose
	private String signature;
	@SerializedName("signatureDate")
	@Expose
	private String signatureDate;

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public Integer getGenerated() {
		return generated;
	}

	public void setGenerated(Integer generated) {
		this.generated = generated;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignatureDate() {
		return signatureDate;
	}

	public void setSignatureDate(String signatureDate) {
		this.signatureDate = signatureDate;
	}

}