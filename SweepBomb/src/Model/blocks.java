package Model;

import java.util.Random;
import java.util.Vector;

public class blocks {
	private int x,y,num;
	private Vector<block> bs;
	private Random rm = new Random();
	//create by the parameterts, or the default.
	public blocks(int[] setting) {
		set(setting);
	}
	public blocks() {
		int[] setting= {9,9,10};
		set(setting);
	}
	//get No.i bomb
	public block getBomb(int i) {
		if(i<bs.size()&&0<=i)
		return bs.get(i);
		
		return null;
	}
	//reset all bomb
	public void reset() {
		for(block i:bs) {
			i.reset();
		}
	}
	//set bomb by the setting
	public void set(int[] setting) {
		bs=new Vector<block>();
		x=setting[0];
		y=setting[1];
		num=setting[2];
		int size=x*y;
		setBlock(size);
		setBomb(size,num);
		setAroundNum();
	}
	//set the other properties
	private void setBlock(int size) {
		for (int i=0;i<size;i++) {
			bs.add(new block());
		}
	}
	private void setBomb(int size,int num) {
		block temp;
		for (int i=0;i<num;i++) {
			if((temp=bs.get(rm.nextInt(size))).isBomb()){
				i--;
				continue;
			}
			temp.setBomb();
		}
	}
	private void setAroundNum() {
		for(int site=0;site<x*y;site++) {
			int count=0;
			block b=bs.get(site);
			for(int i=1;i<9;i++) {
					if(resite(site,i)!=-100) {
						if(bs.get(resite(site,i)).isBomb())
						++count;
					}	
			}
			b.setAround(count);
		}
	}
	//touch the No.i block with return -1(bomb) -2(flag) 1(default)
	public int blockShow(int site) {
		block b=bs.get(site);
		if(b.isBomb()) return -1;
		else if(b.isFlag()) return -2;
		if(b.getAround()==0) spread(site);
		else b.setShow();
		return 1;
	}
	//open the blocks by BFS
	private int spread(int site) {
		block b=bs.get(site);
		if(b.isShow()) return 0;
		b.setShow();
		if(b.getAround()==0) {
			for(int i=1;i<9;i++) 
				if(resite(site,i)!=-100)
				spread(resite(site,i));
			}
		return 1;
	}
	//reset the site,the dir 1-8 show the direction
	private int resite(int site,int dir) {
		switch(dir){
			case 1:if (site/x!=0&&site%x!=0) return site-x-1;break;
			case 2:if (site/x!=0) return site-x;break;
			case 3:if (site/x!=0&&site%x!=x-1) return site-x+1;break;
			case 4:if (site%x!=0) return site-1;break;
			case 5:if (site%x!=x-1) return site+1;break;
			case 6:if (site/x!=y-1&&site%x!=0) return site+x-1;break;
			case 7:if (site/x!=y-1) return site+x;break;
			case 8:if (site/x!=y-1&&site%x!=x-1) return site+x+1;break;
			default:;
		}
		return -100;
	}
	//win or not
	public boolean winCheck() {
		int blockLeft=0;
		for(int i=0;i<x*y;i++) {
			if(!bs.get(i).isShow()) {
				++blockLeft;
			}
		}
		if(blockLeft==num) {
			return true;
		}
		return false;
	}
	//show in the console to demo
	public void show() {
		for(int i=0;i<x*y;i++) {
			System.out.printf("%3d",bs.get(i).getAround());
			if(i%x+1==x) System.out.print("\n");
		}
		System.out.print("\n");
		for(int i=0;i<x*y;i++) {
			if(bs.get(i).isBomb())
			System.out.printf("*");
			else System.out.printf("0");	
			if(i%x+1==x) System.out.print("\n");
		}
		System.out.print("\n");
		for(int i=0;i<x*y;i++) {
			if(bs.get(i).isShow())
			System.out.printf("口");
			else System.out.printf("0");	
			if(i%x+1==x) System.out.print("\n");
		}
		System.out.print("\n");
		for(int i=0;i<x*y;i++) {
			System.out.printf("%3d",bs.get(i).getAround());
			if(i%x+1==x) System.out.print("\n");
		}
	}
	
}
