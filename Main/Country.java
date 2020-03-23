package Main;

public class Country {

	
	private long epoch;
	private String country;
	private int cases;
	private int todayCases;
	private int deaths;
	private int todayDeaths;
	private int recovered;
	private int active;
	private int critical;
	private int casesPerOneMillion;
	public long getEpoch() {
		return epoch;
	}
	public void setEpoch(long epoch) {
		this.epoch = epoch;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getCases() {
		return cases;
	}
	public void setCases(int cases) {
		this.cases = cases;
	}
	public int getTodayCases() {
		return todayCases;
	}
	public void setTodayCases(int todayCases) {
		this.todayCases = todayCases;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public int getTodayDeaths() {
		return todayDeaths;
	}
	public void setTodayDeaths(int todayDeaths) {
		this.todayDeaths = todayDeaths;
	}
	public int getRecovered() {
		return recovered;
	}
	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getCritical() {
		return critical;
	}
	public void setCritical(int critical) {
		this.critical = critical;
	}
	public int getCasesPerOneMillion() {
		return casesPerOneMillion;
	}
	public void setCasesPerOneMillion(int casesPerOneMillion) {
		this.casesPerOneMillion = casesPerOneMillion;
	}
	public Country(long epoch, String country, int cases, int todayCases, int deaths, int todayDeaths, int recovered,
			int active, int critical, int casesPerOneMillion) {
		super();
		this.epoch = epoch;
		this.country = country;
		this.cases = cases;
		this.todayCases = todayCases;
		this.deaths = deaths;
		this.todayDeaths = todayDeaths;
		this.recovered = recovered;
		this.active = active;
		this.critical = critical;
		this.casesPerOneMillion = casesPerOneMillion;
	}
}
