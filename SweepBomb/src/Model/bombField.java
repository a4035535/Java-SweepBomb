package Model;

public class bombField extends blocks{
	//基本参数，长度、高度、雷数。
	private int flagNum;
	private int[] setting= {9,9,10};
	//设置边界值，长宽限制、雷数限制。
	private final int MAX_L=30,MAX_NUM=99,MIN_L=9,MIN_NUM=10;
	//默认设置，初中高级
	private static int[][] defaultSetting= {{9,9,10},{16,16,40},{30,16,99}};
	//格子区对象预创建。	
	bombField(){
		super(defaultSetting[0]);
		setLevel(0);
	}
	//根据0、1、2调整默认难度设置
	public int setLevel(int i) { 
		if(i==1||i==0||i==2) {
			setting=defaultSetting[i];
			return 1;
		}
		return 0;
	}
	//根据给定参数自定难度设置。
	public int setLevel(int a,int b,int c) {
		if(MIN_L<a) setting[0]=MIN_L;
		else if (a>MAX_L) setting[0]=MAX_L;
		else setting[0]=a;
		
		if(MIN_L<b) setting[1]=MIN_L;
		else if (b>MAX_L) setting[1]=MAX_L;
		else setting[1]=b;
		
		if(MIN_NUM<c) setting[2]=MIN_NUM;
		else if (c>MAX_NUM) setting[2]=MAX_NUM;
		else setting[2]=c;
		
		if (setting[2]>setting[0]*setting[1]) setting[2]=setting[0]*setting[1]-1;
		
		return 1;
	}
	//取得设定值，返回int数组
	public int[] getSetting() {
		return setting;
	}
	//返回当前雷区大小
	public int getSize() {
		return setting[0]*setting[1];
	}
	//重构区域，成功返回1；
	public int reStart() {
		set(setting);
		return 1;
	}
	//取得旗子数目
	public int getFlagNum() {
		int count=0;
		for(int i=0;i<this.getSize();i++) {
			if(this.getBomb(i).isFlag())
				++count;
		}
		return count;
	}
	//依据传入的字符串数组调整设置
	public void setSetting(int[] str) {
		setting = str;
	}
}
