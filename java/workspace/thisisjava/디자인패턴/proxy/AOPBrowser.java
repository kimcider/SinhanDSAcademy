package proxy;

public class AOPBrowser implements Browser{
	private String url;
	private WebSite webSite;
	private Runnable start;
	private Runnable end;
	
	AOPBrowser(String url, Runnable start, Runnable end){
		this.url = url;
		this.start = start;
		this.end = end;
	}
	
	public WebSite rendering() {
		start.run();
		
		if(webSite == null) {
			System.out.println(url + ": WebSite 랜더링");
			webSite = new WebSite(url);
		}
		System.out.println(url + ": WebSite 캐싱");
		end.run();
		return webSite;
	}
}
