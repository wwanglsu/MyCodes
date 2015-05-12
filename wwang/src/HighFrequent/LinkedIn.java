package HighFrequent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import wang.c7.Point;
import wang.utility.ArrayUtility;

public class LinkedIn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point[] points=new Point[20];
		for(int i=0; i<20; i++){
			double x=ArrayUtility.getInstance().randomIntRange(1, 20);
			double y=ArrayUtility.getInstance().randomIntRange(1, 20);
			points[i]=new Point(x, y);
		}
		
		for(Point p:findKClosest(points, 5)){
			System.out.println(p);
		}
	}
	
	/****Find the K closest points to the original***********/
	static List<Point> findKClosest(Point[] p, int k){
		PriorityQueue<Point> pq=new PriorityQueue<Point>(10,
			new Comparator<Point>(){
				public int compare(Point a, Point b){
					return (int)((b.x * b.x +b.y*b.y) - (a.x*a.x+a.y*a.y));
				}
			}
		);
		
		for(int i=0; i<p.length; i++){
			if(i<k){
				pq.offer(p[i]);
			}else{
				Point temp=pq.peek();
				if( (p[i].x*p[i].x+p[i].y*p[i].y)-(temp.x*temp.x+temp.y*temp.y) <0 ){
					pq.poll();
					pq.offer(p[i]);
				}
			}
		}
		
		List<Point> x=new ArrayList<>();
		while(!pq.isEmpty()){
			x.add(pq.poll());
		}
		return x;
	}
	/****Find the K closest points to the original***********/
	
	
}
