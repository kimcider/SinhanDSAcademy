package proxy;

public class Chrome implements Browser {

	private String url;
	
	Chrome(String url){
		this.url = url;
	}
	
	@Override
	public WebSite rendering() {
		System.out.println(url + " : WebSite 랜더링");
		WebSite ws = new WebSite(url);
		
		return null;
	}

}
