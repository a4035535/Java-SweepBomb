package Model;

public class bombField extends blocks{
	//�������������ȡ��߶ȡ�������
	private int flagNum;
	private int[] setting= {9,9,10};
	//���ñ߽�ֵ���������ơ��������ơ�
	private final int MAX_L=30,MAX_NUM=99,MIN_L=9,MIN_NUM=10;
	//Ĭ�����ã����и߼�
	private static int[][] defaultSetting= {{9,9,10},{16,16,40},{30,16,99}};
	//����������Ԥ������	
	bombField(){
		super(defaultSetting[0]);
		setLevel(0);
	}
	//����0��1��2����Ĭ���Ѷ�����
	public int setLevel(int i) { 
		if(i==1||i==0||i==2) {
			setting=defaultSetting[i];
			return 1;
		}
		return 0;
	}
	//���ݸ��������Զ��Ѷ����á�
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
	//ȡ���趨ֵ������int����
	public int[] getSetting() {
		return setting;
	}
	//���ص�ǰ������С
	public int getSize() {
		return setting[0]*setting[1];
	}
	//�ع����򣬳ɹ�����1��
	public int reStart() {
		set(setting);
		return 1;
	}
	//ȡ��������Ŀ
	public int getFlagNum() {
		int count=0;
		for(int i=0;i<this.getSize();i++) {
			if(this.getBomb(i).isFlag())
				++count;
		}
		return count;
	}
	//���ݴ�����ַ��������������
	public void setSetting(int[] str) {
		setting = str;
	}
}
