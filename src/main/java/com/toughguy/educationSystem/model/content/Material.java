package com.toughguy.educationSystem.model.content;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.educationSystem.model.AbstractModel;
import com.toughguy.educationSystem.util.JsonUtil;

/**
 * 微信小程序元素获取实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class Material extends AbstractModel {
	 private String title;//图文消息的标题
     private String thumb_media_id;//图文消息的封面图片素材id（必须是永久mediaID）
	 private String author;//作者
	 private String digest;//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
	 private String content;//图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
	 private String url;//图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL
	 private int show_cover_pic;//是否显示封面，0为false，即不显示，1为true，即显示
	 private String thumb_url;//是否显示封面，0为false，即不显示，1为true，即显示
	 private String update_time;//这篇图文消息素材的最后更新时间
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumb_media_id() {
		return thumb_media_id;
	}
	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getShow_cover_pic() {
		return show_cover_pic;
	}
	public void setShow_cover_pic(int show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}
	public String getThumb_url() {
		return thumb_url;
	}
	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	 

}
