package proxy;

public class ChromeCache implements Browser {
	private String url;
	private WebSite site;
	ChromeCache(String url){
		this.url = url;
	}
	@Override
	public WebSite rendering() {
		if(site == null) {
			System.out.println(url + ": WebSite 랜더링");
			site = new WebSite(url);
		}
		System.out.println(url +": WebSite 캐싱");
		return null;
	}

}