package Model;

public class block {
	//�����������Ƿ����ף���Χ�������Ƿ���졣
	private boolean isBomb=false;
	private int aroundBombNum=0;
	private boolean isFlag=false;
	private boolean isShow=false;
	//intֵΪ1��ʾ�����ɹ���
	//�������Ե�set get
	public int reset() {
		isBomb=false;
		aroundBombNum=0;
		isFlag=false;
		isShow=false;
		return 1;
	}
	public boolean isBomb() {
		return isBomb;
	}
	public boolean isFlag() {
		return isFlag;
	}
	public boolean isShow() {
		return isShow;
	}
	public int setBomb() {
		isBomb=true;
		return 1;
	}
	public int setFlag() {
		isFlag=!isFlag;
		return 1;
	}
	public int setShow() {
		isShow=true;
		return 1;
	}
	public int setAround(int i) {
		aroundBombNum=i;
		return 1;
	}
	public int getAround() {
		return aroundBombNum;
	}

}