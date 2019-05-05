package Model;

import java.util.Random;
import java.util.Vector;

public class blocks {
	private int x,y,num;
	private Vector<block> bs;
	private Random rm = new Random();
	//构造函数，有设置和无设置，无设置使用默认参数9、9、16
	public blocks(int[] setting) {
		set(setting);
	}
	public blocks() {
		int[] setting= {9,9,10};
		set(setting);
	}
	//返回第i个bomb
	public block getBomb(int i) {
		if(i<bs.size()&&0<=i)
		return bs.get(i);
		
		return null;
	}
	//重置全部bomb
	public void reset() {
		for(block i:bs) {
			i.reset();
		}
	}
	//根据设置调整bombs，在构造方法中调用。
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
	//设置格子，雷，并赋值周围雷数
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
	//触发第i个格子时进行的操作,-1触雷，-2触旗子，1为正常触碰数字。
	public int blockShow(int site) {
		block b=bs.get(site);
		if(b.isBomb()) return -1;
		else if(b.isFlag()) return -2;
		if(b.getAround()==0) spread(site);
		else b.setShow();
		return 1;
	}
	//内部用展开函数，使用递归辅助blockShow，广度搜索周围格子。
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
	//重设位置，dir表示左上到右下八个方向，主要用于检查是否数组越界和触碰边界。
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
	//胜利检测
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
	//调试用
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
