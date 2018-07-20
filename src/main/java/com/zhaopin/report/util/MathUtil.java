package com.zhaopin.report.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhaopin.report.redis.RedisService;

/**
 * 数学计算方面的工具类
 * @author tigerhoo
 *
 */
public class MathUtil {
	@Autowired
	private RedisService redisService;
	
	/**
	 * 计算离差
	 * @param primitive_df  个体原始得分
	 * @param avarege 所有个体的原始平均数
	 * @return 离差
	 */
	public static float figure_licha(float primitive_df,float avarege)
	{
		return primitive_df-avarege;
	}
	/**
	 * 计算方差
	 * @param licha 离差数组
	 * @param num 所有个体的个数
	 * @return 方差
	 */
	public static float figure_fangcha(float[] licha,int num)
	{
		float[] sumfloat = new float[licha.length]; 
		for(int i=0;i<licha.length;i++)
		{
			sumfloat[i] = (float) Math.pow(licha[i], 2);
		}
		float f = figure_sum(sumfloat);
		return f/num;
	}
	/**
	 * 计算求和
	 * @param score 要计算的数组
	 * @return
	 */
	public static float figure_sum(float[] score)
	{
		float result =0 ;
		for(int i=0;i<score.length;i++)
		{
			result+=score[i];
		}
		return result;
	}
	/**
	 * 计算集合List型的平均数
	 * @param score 要计算的List集合
	 * @return
	 */
	public static float figure_avg(List<Float> score)
	{
		float result =0 ;
		int num =score.size(); 
		for(int i=0;i<num;i++)
		{
			result+=score.get(i);
		}
		if(result==0)
		{
			return 0;
		}
		return result/num;
	}
	/**
	 * 计算集合List型的平均数
	 * @param score 要计算的List集合
	 * @return
	 */
	public static float figure_avg_String(List<String> score)
	{
		float result =0 ;
		int num =score.size(); 
		for(int i=0;i<num;i++)
		{
			result+=Float.valueOf(score.get(i));
		}
		if(result==0)
		{
			return 0;
		}
		return result/num;
	}
	/**
	 * 计算标准差(是一个群体的标准,不针对某个人)
	 * @param fangcha 方差
	 * @return 
	 */
	public static float figure_biaozhuncha(float fangcha)
	{
		return (float) Math.sqrt(fangcha);
	}
	/**
	 * 由全班同学的得分集合，及平均分来获得该班级的标准差(这个指，你计算哪个范围的标准差，就用哪个范围的得分集合)
	 * @param ys_df 全班同学的得分集合
	 * @param avarege 全班同学的平均分
	 * @return
	 */
	public static float figure_biaozhuncha(List<Float> ys_df,float avarege)
	{
		int num = ys_df.size();
		float[] licha = new float[num];
		for (int i=0;i< num;i++) {
			float lc = figure_licha(ys_df.get(i), avarege);
			licha[i] = lc;
		}
		float fangcha = figure_fangcha(licha, num);
		return figure_biaozhuncha(fangcha);
	}
	/**
	 * 由全班同学的得分集合，及平均分来获得该班级的标准差(这个指，你计算哪个范围的标准差，就用哪个范围的得分集合)
	 * @param ys_df 全班同学的得分集合
	 * @param avarege 全班同学的平均分
	 * @return
	 */
	public static float figure_biaozhuncha_String(List<String> ys_df,float avarege)
	{
		int num = ys_df.size();
		float[] licha = new float[num];
		for (int i=0;i< num;i++) {
			float lc = figure_licha(Float.valueOf(ys_df.get(i)), avarege);
			licha[i] = lc;
		}
		float fangcha = figure_fangcha(licha, num);
		return figure_biaozhuncha(fangcha);
	}
	/**
	 * 计算标准分
	 * @param biaozhuncha 标准差
	 * @param df 某个个体的得分
	 * @param avarege 平均分
	 * @return
	 */
	public static float figure_biaozhunfen(float biaozhuncha,float df,float avarege)
	{
		if((df-avarege)==0)
		{
			return 0;
		}
		return (df-avarege)/biaozhuncha;
	}
	/**
	 * 计算某个体百分位
	 * @param order  某个体的分数在整个群体的位置（按从大到小）
	 * @param num 群体个数
	 * @return
	 */
	public static float figure_baifenwei(float order,int num)
	{
		return 100-((100*order-50)/num);
	}
	/**
	 * 计算某个体分化度
	 * @param biaozhuncha 标准差
	 * @param avarege 平均数
	 * @return
	 */
	public static float figure_fenhuadu(float biaozhuncha,float avarege)
	{
		if(biaozhuncha==0)
		{
			return 0;
		}
		return (biaozhuncha/avarege)*100;
	}
	/**
	 * 计算上四分位（说白了就是list的将全部数据按从高到底排序后处在四分之一位置处的值（比如一共99个数从高到低排列，第25个数就是上四分位数））
	 * @param list 要计算中位数的list
	 * @return
	 */
	public static float figure_ssfw(List<Float> list)
	{
		return list.get(list.size()/4);
	}
	/**
	 * 计算下四分位（将全部数据按从高到底排序后处在四分之三位置处的值（比如一共99个数从高到低排列，第75个数就是下四分位数）））
	 * @param list 要计算中位数的list
	 * @return
	 */
	public static float figure_xsfw(List<Float> list)
	{
		return list.get(list.size()/4*3);
	}
	/**
	 * 计算班级中位数（说白了就是list的中间值）
	 * @param list 要计算中位数的list
	 * @return
	 */
	public static float figure_bjzws(List<Float> list)
	{
		return list.get(list.size()/2);
	}
	/**
	 * 计算班级分化程度
	 * @param bzc 标准差
	 * @param avarege 平均值
	 * @return
	 */
	public static float figure_fhcd(float bzc,float avarege)
	{
		if(bzc==0)
		{
			return 0;
		}
		return (bzc/avarege)*100;
	}
	/**
	 * 对float四舍五入，并保留2位小数
	 * 这里的10就是1位小数点,如果要其它位,如2位,这里两个10改成100
	 * @param number
	 * @return
	 */
	public static float round_1(float number)
	{
		return (float)(Math.round(number*100))/100;
	}
	/**
	 * 判断所给得分率所在的成绩层次，并更新层级数据到所给的map中
	 * @param map
	 * @param dfl
	 */
	public static void judgeScoreTier(Map<String,Integer> map,float dfl,String key)
	{
		int bdbNum = map.get("bdbNum"+key);
		int dbNum = map.get("dbNum"+key);
		int yxNum = map.get("yxNum"+key);
		if(dfl<60)
		{
			bdbNum++;
		}
		else if(dfl<85 && dfl>=60)
		{
			dbNum++;
		}
		else
		{
			yxNum++;
		}
		map.put("bdbNum"+key, bdbNum);
		map.put("dbNum"+key, dbNum);
		map.put("yxNum"+key, yxNum);
	}
	/**
	 * 给达标map装载制定key的map数据
	 * @param map 班级&区域达标相关人数map
	 * @param dbTiger_map 达标率等相关数据载体map
	 * @param key 用于区别装载班级还是区域 bj  qy
	 * @param num 样本人数
	 */
	public static void updateTierMap(Map<String,Integer> map,Map<String,Float> dbTiger_map,String key,int num)
	{
		float stunum =  num;
		dbTiger_map.put("zongchengji_"+key+"bdbl", (map.get("bdbNum_zongchengji")/stunum)*100f);//总成绩-班级&区域不达标率
		dbTiger_map.put("zongchengji_"+key+"dbl", (map.get("dbNum_zongchengji")/stunum)*100f);//总成绩-班级&区域达标率
		dbTiger_map.put("zongchengji_"+key+"yxl", (map.get("yxNum_zongchengji")/stunum)*100f);//总成绩-班级&区域优秀率	
		
		dbTiger_map.put("zhishi_"+key+"bdbl", (map.get("bdbNum_zhishi")/stunum)*100f);//知识-班级&区域不达标率
		dbTiger_map.put("zhishi_"+key+"dbl", (map.get("dbNum_zhishi")/stunum)*100f);//知识-班级&区域达标率
		dbTiger_map.put("zhishi_"+key+"yxl", (map.get("yxNum_zhishi")/stunum)*100f);//知识-班级&区域优秀率
		
		dbTiger_map.put("jineng_"+key+"bdbl", (map.get("bdbNum_jineng")/stunum)*100f);//技能-班级&区域不达标率
		dbTiger_map.put("jineng_"+key+"dbl", (map.get("dbNum_jineng")/stunum)*100f);//技能-班级&区域达标率
		dbTiger_map.put("jineng_"+key+"yxl", (map.get("yxNum_jineng")/stunum)*100f);//技能-班级&区域优秀率
		
		dbTiger_map.put("nengli_"+key+"bdbl", (map.get("bdbNum_nengli")/stunum)*100f);//能力-班级&区域不达标率
		dbTiger_map.put("nengli_"+key+"dbl", (map.get("dbNum_nengli")/stunum)*100f);//能力-班级&区域达标率
		dbTiger_map.put("nengli_"+key+"yxl", (map.get("yxNum_nengli")/stunum)*100f);//能力-班级&区域优秀率
	}

	public static Map<Integer , Integer > getHotBaseIndex(int[] s)
	{
		Map<Integer , Integer > map = new HashMap<Integer,Integer>();
		for(int i : s){
		  Integer c = map.get(i);
		  if( c==null){ 
			  map.put(i,1);
		  }
		  else{
			  map.put(i,c+1);
		  }
		}
		return map;
	}
}
