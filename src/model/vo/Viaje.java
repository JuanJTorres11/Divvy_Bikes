package model.vo;

import java.time.LocalDateTime;

/**
 * Representation of a Trip object
 */
public class Viaje implements Comparable <Viaje>
{
	public enum Tipo
	{
		Subscriber,
		Customer
	}

	public enum Genero
	{
		Female,
		Male
	}

	private Tipo usertype;

	private Genero gender;

	private int tripId;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private int bikeId;

	private int tripDuration;

	private int fromStationId;

	private String fromStationName;

	private int toStationId;

	private String toStationName;

	private int bithyear;

	public Viaje ()
	{
	}
	public Viaje (int tripId, LocalDateTime start, LocalDateTime end, int bikeId, int duration, int FromStId, String FromSt, int ToStId, String ToSt, String tipo, String genero, int year)
	{
		this.tripId = tripId;
		startTime = start;
		endTime = end;
		this.bikeId = bikeId;
		tripDuration = duration;
		fromStationId = FromStId;
		fromStationName = FromSt;
		toStationId = ToStId;
		toStationName = ToSt;
		usertype = Tipo.valueOf(tipo);
		gender = null;
		if (genero != null)
			gender = Genero.valueOf(genero);
		bithyear = year;
	}

	/**
	 * @return the usertype
	 */
	public Tipo getUsertype() {
		return usertype;
	}
	/**
	 * @return the gender
	 */
	public Genero getGender() {
		return gender;
	}
	/**
	 * @return the tripId
	 */
	public int getTripId() {
		return tripId;
	}
	/**
	 * @return the startTime
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}
	/**
	 * @return the endTime
	 */
	public LocalDateTime getEndTime() {
		return endTime;
	}
	/**
	 * @return the bikeId
	 */
	public int getBikeId() {
		return bikeId;
	}
	/**
	 * @return the tripDuration
	 */
	public int getTripDuration() {
		return tripDuration;
	}
	/**
	 * @return the fromStationId
	 */
	public int getFromStationId() {
		return fromStationId;
	}
	/**
	 * @return the fromStationName
	 */
	public String getFromStationName() {
		return fromStationName;
	}
	/**
	 * @return the toStationId
	 */
	public int getToStationId() {
		return toStationId;
	}
	/**
	 * @return the toStationName
	 */
	public String getToStationName() {
		return toStationName;
	}
	/**
	 * @return the bithyear
	 */
	public int getBithyear() {
		return bithyear;
	}
	/**
	 * @param usertype the usertype to set
	 */
	public void setUsertype(Tipo usertype) {
		this.usertype = usertype;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(Genero gender) {
		this.gender = gender;
	}
	/**
	 * @param tripId the tripId to set
	 */
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	/**
	 * @param bikeId the bikeId to set
	 */
	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}
	/**
	 * @param tripDuration the tripDuration to set
	 */
	public void setTripDuration(int tripDuration) {
		this.tripDuration = tripDuration;
	}
	/**
	 * @param fromStationId the fromStationId to set
	 */
	public void setFromStationId(int fromStationId) {
		this.fromStationId = fromStationId;
	}
	/**
	 * @param fromStationName the fromStationName to set
	 */
	public void setFromStationName(String fromStationName) {
		this.fromStationName = fromStationName;
	}
	/**
	 * @param toStationId the toStationId to set
	 */
	public void setToStationId(int toStationId) {
		this.toStationId = toStationId;
	}
	/**
	 * @param toStationName the toStationName to set
	 */
	public void setToStationName(String toStationName) {
		this.toStationName = toStationName;
	}
	/**
	 * @param bithyear the bithyear to set
	 */
	public void setBithyear(int bithyear) {
		this.bithyear = bithyear;
	}
	@Override
	public int compareTo(Viaje tri)
	{
		if (tri.getBikeId() == this.bikeId)
			return 0;
		else if (this.bikeId < tri.getBikeId())
			return -1;
		else
			return 1;
	}
}