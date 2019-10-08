package shiyan4;

public class function {

	public static double newmoney(double people,double money,double year){
		int i,m;
		double y=2019-year;
		if(money>=26000) {
			return money;
		}
		else {
		//年份
			for(m=0;m<y;m++)
			{
				money=money+50;
				if(money>=26000) {
					return 26000;
				}
			}
		//赡养人口
		for(i=1;i<=people;i++)
		{
			money=money+100;
				if(money>=26000) {
				return 26000;
			}
			
		}
		return money;
	}
	}
}
