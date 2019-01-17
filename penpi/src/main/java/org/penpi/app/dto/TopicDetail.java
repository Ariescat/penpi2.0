package org.penpi.app.dto;

import java.util.List;

public class TopicDetail extends TopicEx {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean ifFollow; //是否关注该话题
	
	private Integer followerAmount;
	private List<Follower> followers;

	private List<NoteEx> notes;

	public class Follower {
		Integer userId;
		String userNm;
		Integer userHeadPictId;
		String userHeadPictStr;

		public Follower(Integer userId, String userNm, Integer userHeadPictId, String userHeadPictStr) {
			super();
			this.userId = userId;
			this.userNm = userNm;
			this.userHeadPictId = userHeadPictId;
			this.userHeadPictStr = userHeadPictStr;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public Integer getUserHeadPictId() {
			return userHeadPictId;
		}

		public void setUserHeadPictId(Integer userHeadPictId) {
			this.userHeadPictId = userHeadPictId;
		}

		public String getUserHeadPictStr() {
			return userHeadPictStr;
		}

		public void setUserHeadPictStr(String userHeadPictStr) {
			this.userHeadPictStr = userHeadPictStr;
		}

		public String getUserNm() {
			return userNm;
		}

		public void setUserNm(String userNm) {
			this.userNm = userNm;
		}

	}

	public List<NoteEx> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteEx> notes) {
		this.notes = notes;
	}

	public Integer getFollowerAmount() {
		return followerAmount;
	}

	public void setFollowerAmount(Integer followerAmount) {
		this.followerAmount = followerAmount;
	}

	public List<Follower> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Follower> followers) {
		this.followers = followers;
	}

	public Boolean getIfFollow() {
		return ifFollow;
	}

	public void setIfFollow(Boolean ifFollow) {
		this.ifFollow = ifFollow;
	}

}
