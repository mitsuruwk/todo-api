package resource.out;

@lombok.Data
public class StatusMsg {
	private int status = 200;
	private String message = "None";
	private String error = "None";
}
