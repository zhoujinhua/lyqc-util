package com.liyun.car.common.entity;

import java.util.List;

import com.liyun.car.common.contants.Constants;


/**
 * 分页Page对象
 * @author zhoufei
 *
 * @param <E>
 */
public class Page<E> {

	private int count; //记录数
	private int size = Constants.DEFAULT_PAGE_SIZE; //每页记录数
	private int num;   //当前页号
	private List<E> items; //记录内容
	
	
	public Page() {
		super();
	}
	
	public Page(int count, int size) {
		super();
		this.count = count;
		this.size = size;
	}


	public Page(int count, int num, List<E> items) {
		this.count = count;
		this.num = num;
		this.items = items;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		//不允许页号小于1
		if(num < 1){
			num = 1;
		}
		this.num = num;
	}
	public List<E> getItems() {
		return items;
	}
	public void setItems(List<E> items) {
		this.items = items;
	}
	/**
	 * 获取页数
	 * @return
	 */
	public int getPageCount(){
		int tmp = (count%size);
		return tmp==0?count/size:(count-tmp)/size+1;
	}
	
	/**
	 * 下一页的page对象
	 * @return
	 */
	public Page<E> next(){
		Page<E> page = new Page<E>(this.count,this.size);
		page.setNum(this.hasNextPage()?this.num+1:this.num);
		return page;
	}
	/**
	 * 上一页的page对象
	 * @return
	 */
	public Page<E> previous(){
		Page<E> page = new Page<E>(this.count,this.size);
		page.setNum(this.hasPreviousPage()?this.num-1:this.num);
		return page;
	}
	/**
	 * 是否是第一页
	 * @return
	 */
	public boolean isFirstPage(){
		return num == 1;
	}
	
	/**
	 * 是否是最后一页
	 * @return
	 */
	public boolean isLastPage(){
		return num >= this.getPageCount();
	}

	/**
	 * 检查是否有下一页
	 * @return
	 */
	public boolean hasNextPage(){
		return (num < this.getPageCount());
	}
	
	/**
	 * 检查是否有上一页
	 * @return
	 */
	public boolean hasPreviousPage(){
		return (num > 1);
	}
	
	public int getStart() {
		return (this.getNum()-1)*this.getSize();
	}

	public int getEnd() {
		return (this.getNum()*this.getSize());
	}
}
