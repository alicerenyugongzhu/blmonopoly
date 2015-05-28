package com.alice.blmonopoly.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import android.util.Log;
import com.alice.blmonopoly.R;

//Think about whether I need to use the Abstract Class
public class GameInfo {
	//Static value
	int KNOWN_MAX=5;
	
	//Random
	Random rand;
	
	//Map event number definition
	int startNumber;
	int makeFriendNumber;
	int changeCareerNumber;
	int dateNumber;
	int luckyDrawNumber;
	int moveForwardNumber;
	int moveBackNumber;
	int taskNumber;
	int endNumber;
	
	public enum gameEvent{
		START,
		MAKE_FRIEND,
		CHANGE_CAREER,
		DATE,
		LUCKY_DRAW,
		MOVE_FORWARD,
		MOVE_BACK,
		TASK,
		END
	};
	
	public enum gameCharacter{
		Baimugui("百目鬼", 55, Career.hooligan, R.drawable.baimugui, 0, 0),
		GaoYu_QiuRen("高羽秋仁", 40, Career.journalist, R.drawable.qiuren, 0, 1),    //高羽秋仁
		MaJian_LongYi("麻见隆一", 95, Career.hooligan, R.drawable.majian, 0, 2),   //麻见
		ShiDai("矢代", 15, Career.hooligan, R.drawable.shidai, 0, 3),          //矢代
		XiaoNiaoYou_Zhang("小鸟游彰", 40, Career.student, R.drawable.xiaoniao, 0, 4),  //小鸟游彰
		GaoChuan_ZhiYe("缟川直也", 90, Career.student, R.drawable.gaochuan, 0, 5),     //缟川直也
		ShouYe("守夜", 70, Career.staff, R.drawable.shouye, 0, 6),          //守夜
		LongCheng("隆成", 50, Career.staff, R.drawable.longcheng, 0, 7),       //隆成
		Heilai_Lu("黑赖陆", 80, Career.doctor, R.drawable.heilai, 0, 8),        //黑赖陆
		ChengGu_ZhongChen("城古忠臣", 25, Career.secretary, R.drawable.chenggu, 0, 9),   //城古忠臣
		YouChuan_YangYi("有川洋一", 60, Career.lawyer, R.drawable.youchuan, 0, 10),     //有川洋一
		YuQi_ShaoTai("御崎韶太", 30, Career.student, R.drawable.yuqi, 0, 11),        //御崎韶太
		XiaoYeSi_Lv("小野寺律", 25, Career.editor, R.drawable.xiaoye, 0, 12),     //小野寺律
		GaoYe_ZongZheng("高野宗政", 80, Career.manager, R.drawable.gaoye, 0, 13), //高野宗政
		JiuShi_XiaoRen("久世晓人", 80, Career.noble, R.drawable.jiushi, 0, 14),  //久世晓人
		GuiMu_ZhiZhi("桂木智之", 50, Career.noble, R.drawable.guimu, 0, 15),    //桂木智之
		QianCun_JiQing("迁村基晴", 60, Career.student, R.drawable.qiancun, 0, 16),  //迁村基晴
		LianJian_Jing("莲见晶", 40, Career.student, R.drawable.lianjian, 0, 17),   //莲见晶
		XiTiao_GaoRen("西条高人", 35, Career.star, R.drawable.xitiao, 0, 18),   //西条高人
		GaoYuan_YiChen("高远一辰", 85, Career.student, R.drawable.gaoyuan, 0, 19),  //高远一辰
		ZhongSi("柊司", 35, Career.teacher, R.drawable.zhongsi, 0, 20),         //柊司
		ShenWei("慎尾", 45, Career.bar_owner, R.drawable.shenwei, 0, 21),         //慎尾
		HeZhi("和智", 80, Career.manager, R.drawable.hezhi, 0, 22),           //和智
		XiangLe_Ling("向乐玲", 35, Career.staff, R.drawable.xiangle, 0, 23),    //向乐玲
		ShengHu("圣湖", 75, Career.star, R.drawable.shenghu, 0, 24),         //圣湖
		YiTiao_LongMa("一条龙马", 75, Career.star, R.drawable.yitiao, 0, 25),   //一条龙马
		QuanShui("泉水", 30, Career.star, R.drawable.quanshui, 0, 26);         //泉水
		//Zhenbi_qi()     //真壁齐
		//Qingye()    //晴也
		
		private String name;
		private int    level;
		private Career career;
		private int    index;
		private int    picture;
		private int    favor;
		
		private gameCharacter(String name, int level, Career career, int picture, int favor, int index){
			this.name=name;
			this.level = level;
			this.career = career;
			this.picture = picture;
			this.favor = favor;
			this.index=index;
		}
		
		public static String getName(int index) {
            for (gameCharacter c : gameCharacter.values()) {
                if (c.getIndex() == index) {
                    return c.name;
                }
            }
            return null;
        }
		
		public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
        
        public void setLevel(int level){
        	this.level = level;
        }
        
        public void setFavor(int favor){
        	this.favor = favor;
        }
        
        public int getFavor(){
        	return favor;
        }
        
        public static int getLevel(int index){
            for (gameCharacter c : gameCharacter.values()) {
                if (c.getIndex() == index) {
                    return c.level;
                }
            }
            return -1;
        }
        
        public void setCareer(Career career){
        	this.career = career;
        }
        
        public static Career getCareer(int index) {
            for (gameCharacter c : gameCharacter.values()) {
                if (c.getIndex() == index) {
                    return c.career;
                }
            }
            return null;
        }
        
        public static int getPicture(int index) {
            for (gameCharacter c : gameCharacter.values()) {
                if (c.getIndex() == index) {
                    return c.picture;
                }
            }
            return -1;
        }
        
        public int getPicture(){
        	return picture;
        }
	}
	
	public enum Career {
		journalist("记者", 0),
		teacher("老师", 1),
		student("学生", 2),
		doctor("医生", 3),
		secretary("秘书", 4),
		//sales("销售", 5),
		manager("经理", 5),
		hooligan("黑社会", 6),
		writer("作家", 7),
		//painter("画家", 9),
		//musician("音乐家", 10),
		staff("职员", 8),
		star("明星", 9),
		bar_owner("酒吧老板", 10),
		editor("编辑", 11),
		//farmer("农民",15),
		police("警察", 12),
		noble("贵族", 13),
		lawyer("律师", 14),
		waiter("服务员", 15);
		
		private String careerName;
		private int index;
		
		private Career(String careerName, int index){
			this.careerName = careerName;
			this.index = index;
		}
		
		public static String getCareerName(int index){
            for (Career c : Career.values()) {
                if (c.getIndex() == index) {
                    return c.careerName;
                }
            }
            return null;
		}
		
		public static int getIndex(String careerName){
			for (Career c : Career.values()){
				if(c.getCareerName() == careerName){
					return c.index;
				}
			}
			return -1;
		}
		
		public String getCareerName(){
			return careerName;
		}
		public int getIndex(){
			return index;
		}
		public static Career getCareer(int index){
			for(Career c: Career.values()){
				if(c.getIndex() == index){
					return c;
				}
			
			}
			return null;
		}
		
	}
	
	public enum endEvent{
		married,
		cohabit,
		boyfriends,
		harem,
		single
	}
	
	public enum Favor {
		
	}
	
	public enum StoryFriend{
		jouralist_baimugui(Career.journalist, gameCharacter.Baimugui, R.string.jouralist_baimugui, 60, 0),
		jouralist_gaoyu(Career.journalist, gameCharacter.GaoYu_QiuRen, R.string.journalist_gaoyu, 50, 1),
		journalist_majian(Career.journalist, gameCharacter.MaJian_LongYi, R.string.jouralist_majian, 40, 2),
		journalist_quanshui(Career.journalist, gameCharacter.QuanShui, R.string.journalist_quanshui, 50, 3),
		journalist_xitiao(Career.journalist, gameCharacter.XiTiao_GaoRen, R.string.journalist_xitiao, 60, 4),
		doctor_chenggu(Career.doctor, gameCharacter.ChengGu_ZhongChen, R.string.doctor_chenggu, 40, 5),
		doctor_heilai(Career.doctor, gameCharacter.Heilai_Lu, R.string.doctor_heilai, 50, 6),
		doctor_gaochuan(Career.doctor, gameCharacter.GaoChuan_ZhiYe, R.string.doctor_gaochuan, 50, 7),
		doctor_xiaoniaoyou(Career.doctor, gameCharacter.XiaoNiaoYou_Zhang, R.string.doctor_xiaoniaoyou, 50, 8),
		doctor_shidai(Career.doctor, gameCharacter.ShiDai, R.string.doctor_shidai, 50, 9),
		bar_shenwei(Career.bar_owner, gameCharacter.ShenWei, R.string.bar_shenwei, 50, 10),
		bar_gaochuan(Career.bar_owner, gameCharacter.GaoChuan_ZhiYe, R.string.bar_gaochuan, 50, 11),
		bar_xitiao(Career.bar_owner, gameCharacter.XiTiao_GaoRen, R.string.bar_xitiao, 50, 12),
		bar_shouye(Career.bar_owner, gameCharacter.ShouYe, R.string.bar_shouye, 55, 13),
		bar_hezhi(Career.bar_owner, gameCharacter.HeZhi, R.string.bar_hezhi, 50, 14),
		editor_gaoye(Career.editor, gameCharacter.GaoYe_ZongZheng, R.string.editor_gaoye, 50, 15),
		editor_xiaoyesi(Career.editor, gameCharacter.XiaoYeSi_Lv, R.string.editor_xiaoyesi, 60, 16),
		editor_gaoyu(Career.editor, gameCharacter.GaoYu_QiuRen, R.string.editor_gaoyu, 60, 17),
		editor_lianjian(Career.editor, gameCharacter.LianJian_Jing, R.string.editor_lianjian, 50, 18),
		editor_qiancun(Career.editor, gameCharacter.QianCun_JiQing, R.string.editor_qiancun, 50, 19),
		hooligan_majian(Career.hooligan, gameCharacter.MaJian_LongYi, R.string.hooligan_majian, 50, 20),
		hooligan_shidai(Career.hooligan, gameCharacter.ShiDai, R.string.hooligan_shidai, 50, 21),
		hooligan_baimugui(Career.hooligan, gameCharacter.Baimugui, R.string.hooligan_baimugui, 40, 22),
		hooligan_longcheng(Career.hooligan, gameCharacter.LongCheng, R.string.hooligan_longcheng, 50, 23),
		hooligan_shenwei(Career.hooligan, gameCharacter.ShenWei, R.string.hooligan_shenwei, 50, 24),
		lawyer_youchuan(Career.lawyer, gameCharacter.YouChuan_YangYi, R.string.lawyer_youchuan, 50, 25),
		lawyer_xiaoniaoyou(Career.lawyer, gameCharacter.XiaoNiaoYou_Zhang, R.string.lawyer_xiaoniaoyou, 50, 26),
		lawyer_baimugui(Career.lawyer, gameCharacter.Baimugui, R.string.lawyer_baimugui, 55, 27),
		lawyer_longcheng(Career.lawyer, gameCharacter.LongCheng, R.string.lawyer_longcheng, 50, 28),
		lawyer_hezhi(Career.lawyer, gameCharacter.HeZhi, R.string.lawyer_hezhi, 50, 29),
		manager_gaoye(Career.manager, gameCharacter.GaoYe_ZongZheng, R.string.manager_gaoye, 50, 30),
		manager_hezhi(Career.manager, gameCharacter.HeZhi, R.string.manager_hezhi, 50, 31),
		manager_jiushi(Career.manager, gameCharacter.JiuShi_XiaoRen, R.string.manager_jiushi, 50, 32),
		manager_guimu(Career.manager, gameCharacter.GuiMu_ZhiZhi, R.string.manager_guimu, 50, 33),
		manager_xiangle(Career.manager, gameCharacter.XiangLe_Ling, R.string.manager_xiangle, 55, 34),
		noble_guimu(Career.noble, gameCharacter.GuiMu_ZhiZhi, R.string.noble_guimu, 45, 35),
		noble_jiushi(Career.noble, gameCharacter.JiuShi_XiaoRen, R.string.noble_jiushi, 50, 36),
		noble_qiancun(Career.noble, gameCharacter.QianCun_JiQing, R.string.noble_qiancun, 50, 37),
		noble_majian(Career.noble, gameCharacter.MaJian_LongYi, R.string.noble_majian, 50, 38),
		noble_shenwei(Career.noble, gameCharacter.ShenWei, R.string.noble_shenwei, 60, 39),
		police_baimugui(Career.police, gameCharacter.Baimugui, R.string.police_baimugui, 40, 40),
		police_majian(Career.police, gameCharacter.MaJian_LongYi, R.string.police_majian, 40, 41),
		police_shidai(Career.police, gameCharacter.ShiDai, R.string.police_shidai, 40, 42),
		police_longcheng(Career.police, gameCharacter.LongCheng, R.string.police_longcheng, 40, 43),
		police_shenwei(Career.police, gameCharacter.ShenWei, R.string.police_shenwei, 50, 44),
		secretary_chenggu(Career.secretary, gameCharacter.ChengGu_ZhongChen, R.string.secretary_chenggu, 50, 45),
		secretary_hezhi(Career.secretary, gameCharacter.HeZhi, R.string.secretary_hezhi, 55, 46),
		secretary_gaoye(Career.secretary, gameCharacter.GaoYe_ZongZheng, R.string.secretary_gaoye, 55, 47),
		secretary_xiangle(Career.secretary, gameCharacter.XiangLe_Ling, R.string.secretary_xiangle, 50, 48),
		secretary_shouye(Career.secretary, gameCharacter.ShouYe, R.string.secretary_shouye, 50, 49),
		star_shenghu(Career.star, gameCharacter.ShengHu, R.string.star_shenghu, 50, 50),
		star_quanshui(Career.star, gameCharacter.QuanShui, R.string.star_quanshui, 50, 51),
		star_xitiao(Career.star, gameCharacter.XiTiao_GaoRen, R.string.star_xitiao, 45, 52),
		star_yitiao(Career.star, gameCharacter.YiTiao_LongMa, R.string.star_yitiao, 40, 53),
		star_gaoyu(Career.star, gameCharacter.GaoYu_QiuRen, R.string.star_gaoyu, 50, 54),
		student_gaoyuan(Career.student, gameCharacter.GaoYuan_YiChen, R.string.student_gaoyuan, 50, 55),
		student_yuqi(Career.student, gameCharacter.YuQi_ShaoTai, R.string.student_yuqi, 50, 56),
		student_gaochuan(Career.student, gameCharacter.GaoChuan_ZhiYe, R.string.student_gaochuan, 50, 57),
		student_xiaoniaoyou(Career.student, gameCharacter.XiaoNiaoYou_Zhang, R.string.student_xiaoniaoyou, 50, 58),
		student_zhongsi(Career.student, gameCharacter.ZhongSi, R.string.student_zhongsi, 55, 59),
		teacher_gaoyuan(Career.teacher, gameCharacter.GaoYuan_YiChen, R.string.teacher_gaoyuan, 50, 60),
		teacher_yuqi(Career.teacher, gameCharacter.YuQi_ShaoTai, R.string.teacher_yuqi, 50, 61),
		teacher_lianjian(Career.teacher, gameCharacter.LianJian_Jing, R.string.teacher_lianjian, 50, 62),
		teacher_youchuan(Career.teacher, gameCharacter.QianCun_JiQing, R.string.teacher_qiancun, 50, 63),
		teacher_zhongsi(Career.teacher, gameCharacter.ZhongSi, R.string.teacher_zhongsi, 50, 64),
		staff_heilai(Career.staff, gameCharacter.Heilai_Lu, R.string.staff_heilai, 50, 65),
		staff_shouye(Career.staff, gameCharacter.ShouYe, R.string.staff_shouye, 50, 66),
		staff_longcheng(Career.staff, gameCharacter.LongCheng, R.string.staff_longcheng, 50, 67),
		staff_xiaoyesi(Career.staff, gameCharacter.XiaoYeSi_Lv, R.string.staff_xiaoyesi, 50, 68),
		staff_zhongsi(Career.staff, gameCharacter.ZhongSi, R.string.staff_zhongsi, 50, 69),
		writer_xiaoyesi(Career.writer, gameCharacter.XiaoYeSi_Lv, R.string.writer_xiaoyesi, 60, 70),
		writer_gaoye(Career.writer, gameCharacter.GaoYe_ZongZheng, R.string.writer_gaoye, 50, 71),
		writer_shenghu(Career.writer, gameCharacter.ShengHu, R.string.writer_shenghu, 55, 72),
		writer_quanshui(Career.writer, gameCharacter.QuanShui, R.string.writer_quanshui, 55, 73),
		writer_yitiao(Career.writer, gameCharacter.YiTiao_LongMa, R.string.writer_yitiao, 60, 74);
		
		private Career career;
		private gameCharacter character;
		private int story;
		private int index;
		private int favorLevel;
		private StoryFriend(Career career, gameCharacter character,
				int story, int favorLevel, int index){
			this.career = career;
			this.character = character;
			this.story = story;
			this.favorLevel = favorLevel;
			this.index = index;
		}
		

		public static int getStoryFriend(Career career, gameCharacter character){
			for (StoryFriend i: StoryFriend.values()){
				if(i.getCharacter() == character){
					if(i.getCareer() == career){
						return i.story;
					}
				}
			}
			return -1;
		}
		
		private Career getCareer() {
			return career;
		}

		private gameCharacter getCharacter(){
			return character;
		}
		
		public static int getFavorLevel(Career career, gameCharacter character){
			for (StoryFriend i: StoryFriend.values()){
				if(i.getCharacter() == character){
					if(i.getCareer() == career){
						return i.favorLevel;
					}
				}
			}
			return -1;
		}
	}
	
	public enum LuckyDraw {
		visiting_to(2, 2, R.string.visiting_to, 0),   //GLevel, FavorLevel, event, index
		be_visited(-2, 2, R.string.be_visited, 1),
		working_with(2, 2, R.string.working_with, 2),
		be_worked(-2, 2, R.string.be_worked, 3),
		clean_house(2, 2, R.string.clean_house, 4),
		clean_house2(-2, 2, R.string.clean_house2, 5),
		telling_to(2, 2, R.string.telling_to, 6),
		be_told(-2, 2, R.string.be_told, 7),
		call_to(2, 2, R.string.call_to, 8),
		be_called(-2, 2, R.string.be_called, 9),
		present_to(2, 2, R.string.present_to, 10),
		get_present(-2, 2, R.string.get_present, 11),
		invite_to(2, 2, R.string.invite_to,12),
		be_invited(-2, 2, R.string.be_invited, 13);
		
		private int gLevel;
		private int favorLevel;
		private int event;
		private int index;
		
		private LuckyDraw(int gLevel, int favorLevel, int event, int index){
			this.gLevel = gLevel;
			this.favorLevel = favorLevel;
			this.event = event;
			this.index = index;
		}
		
		public static int getFavorLevel(int index){
			for(LuckyDraw c: LuckyDraw.values()){
				if(c.index == index){
					return c.favorLevel;
				}
			}
			return -1;
		}
		public static int getGLevel(int index){
			for(LuckyDraw c: LuckyDraw.values()){
				if(c.index == index){
					return c.gLevel;
				}
			}
			return -1;
		}
		public static int getEvent(int index){
			for(LuckyDraw c: LuckyDraw.values()){
				if(c.index == index){
					return c.event;
				}
			}
			return -1;
		}

	}
	
	public enum Task {
		task1(R.string.task1, R.string.task1_c1, R.string.task1_c2, 0, 0),
		task2(R.string.task2, R.string.task2_c1, R.string.task2_c2, 0, 1);
		private int task_question;
		private int task_choice_1;
		private int task_choice_2;
		private int result;
		private int index;
		
		private Task(int task_question, int task_c1, int task_c2, int result, int index){
			this.task_question = task_question;
			this.task_choice_1 = task_c1;
			this.task_choice_2 = task_c2;
			this.result = result;
			this.index = index;
		}
		
		public int getResult(){
			return result;
		}
		
		public int getQuestion(){
			return task_question; 
		}
		
		public int getR1(){
			return task_choice_1;
		}
		
		public int getR2(){
			return task_choice_2;
		}
		
		public static Task getTask(int index){
			for(Task c: Task.values()){
				if(c.index == index){
					return c;
				}
			}
			return null;
		}
	}
	
    String playerName;
    int playerGValue;
    Career playerCareer;
    int playerPhoto;
    ArrayList<gameCharacter> knownList;
    Hashtable<Career, gameCharacter[]> careerhash;
    ArrayList storyFriend;
    
	
	public GameInfo(){
		knownList = new ArrayList<gameCharacter>();
		//Init career and make friends relationship
		InitMakeFriends();
		
	}
	
	private void InitMakeFriends() {
		careerhash = new Hashtable<Career, gameCharacter[]>();
		
		gameCharacter name[][] = new gameCharacter[Career.values().length][KNOWN_MAX];
		int [] i = getRandomIndex(KNOWN_MAX);
		name[0][i[0]] =  gameCharacter.Baimugui;     //interview the killer for his father
		name[0][i[1]] = gameCharacter.GaoYu_QiuRen;  //know another journalist
		name[0][i[2]] = gameCharacter.MaJian_LongYi; //interview the drug deal
		name[0][i[3]] = gameCharacter.QuanShui;      //interview the new pop star 
		name[0][i[4]] = gameCharacter.XiTiao_GaoRen; //interview who win the second place for whom the most want to be hugged
		careerhash.put(Career.journalist, name[0]);
		name[1][i[0]] = gameCharacter.ChengGu_ZhongChen;  //patient who has the cleanliness
		name[1][i[1]] = gameCharacter.Heilai_Lu;          //know another doctor
		name[1][i[2]] = gameCharacter.GaoChuan_ZhiYe;     //has a intern doctor who is not graduated from the university
		name[1][i[3]] = gameCharacter.XiaoNiaoYou_Zhang;  //University friends
		name[1][i[4]] = gameCharacter.ShiDai;             //treat who has been hurt by gun
		careerhash.put(Career.doctor, name[1]);
		name[2][i[0]] = gameCharacter.ShenWei;            //A new bartender
		name[2][i[1]] = gameCharacter.GaoChuan_ZhiYe;     //two girls are argue for him
		name[2][i[2]] = gameCharacter.XiTiao_GaoRen;      //drunk with the friends in the bar
		name[2][i[3]] = gameCharacter.ShouYe;             //play piano in the bar
		name[2][i[4]] = gameCharacter.HeZhi;              //always drink here
		careerhash.put(Career.bar_owner, name[2]);
		name[3][i[0]] = gameCharacter.GaoYe_ZongZheng;    //Know another editor
		name[3][i[1]] = gameCharacter.XiaoYeSi_Lv;        //Know another editor
		name[3][i[2]] = gameCharacter.GaoYu_QiuRen;       //Cooperate to Get the news
		name[3][i[3]] = gameCharacter.LianJian_Jing;      //get the thesis from him
		name[3][i[4]] = gameCharacter.QianCun_JiQing;     //get the thesis from him
		careerhash.put(Career.editor, name[3]);
		name[4][i[0]] = gameCharacter.MaJian_LongYi;      //know another hooligan
		name[4][i[1]] = gameCharacter.ShiDai;             //know another hooligan
		name[4][i[2]] = gameCharacter.Baimugui;           //know another hooligan
		name[4][i[3]] = gameCharacter.LongCheng;          //know another hooligan
		name[4][i[4]] = gameCharacter.ShenWei;            //want to know news from him
		careerhash.put(Career.hooligan, name[4]);
		name[5][i[0]] = gameCharacter.YouChuan_YangYi;    //know another lawyer
		name[5][i[1]] = gameCharacter.XiaoNiaoYou_Zhang;  //know another lawyer
		name[5][i[2]] = gameCharacter.Baimugui;           //help him defend in the court
		name[5][i[3]] = gameCharacter.LongCheng;      //help him to resolve the case   
		name[5][i[4]] = gameCharacter.HeZhi;              //help him to resolve the case
		careerhash.put(Career.lawyer, name[5]);
		name[6][i[0]] = gameCharacter.GaoYe_ZongZheng;    //know another manager
		name[6][i[1]] = gameCharacter.HeZhi;              //know another manager
		name[6][i[2]] = gameCharacter.JiuShi_XiaoRen;     //know the noble in the party
		name[6][i[3]] = gameCharacter.GuiMu_ZhiZhi;       //know the manager in the party
		name[6][i[4]] = gameCharacter.XiangLe_Ling;       //try to find star to endorsement
		careerhash.put(Career.manager, name[6]);
		name[7][i[0]] = gameCharacter.GuiMu_ZhiZhi;        //the housekeeper for you
		name[7][i[1]] = gameCharacter.JiuShi_XiaoRen;      //know another noble
		name[7][i[2]] = gameCharacter.QianCun_JiQing;      //introduced by his father
		name[7][i[3]] = gameCharacter.MaJian_LongYi;       //know in the party
		name[7][i[4]] = gameCharacter.ShenWei;             //want to treat you
		careerhash.put(Career.noble, name[7]);
		name[8][i[0]] = gameCharacter.Baimugui;          //know another police
		name[8][i[1]] = gameCharacter.MaJian_LongYi;     //want to arrest him
		name[8][i[2]] = gameCharacter.ShiDai;            //want to arrest him
		name[8][i[3]] = gameCharacter.LongCheng;         //arrest him. but he didn't kill anyone
		name[8][i[4]] = gameCharacter.ShenWei;           //want to arrest him
		careerhash.put(Career.police, name[8]);
		name[9][i[0]] = gameCharacter.ChengGu_ZhongChen;   //know another secretary
		name[9][i[1]] = gameCharacter.HeZhi;               //new manager
		name[9][i[2]] = gameCharacter.GaoYe_ZongZheng;     //new manager
		name[9][i[3]] = gameCharacter.XiangLe_Ling;        //working together
		name[9][i[4]] = gameCharacter.ShouYe;              //learn from him
		careerhash.put(Career.secretary, name[9]);
		name[10][i[0]] = gameCharacter.ShengHu;             //know another star
		name[10][i[1]] = gameCharacter.QuanShui;            //know another star
		name[10][i[2]] = gameCharacter.XiTiao_GaoRen;       //know another star
		name[10][i[3]] = gameCharacter.YiTiao_LongMa;       //know another star
		name[10][i[4]] = gameCharacter.GaoYu_QiuRen;        //come to interview you
		careerhash.put(Career.star, name[10]);
		name[11][i[0]] = gameCharacter.GaoYuan_YiChen;      //know another student
		name[11][i[1]] = gameCharacter.YuQi_ShaoTai;        //know another student
		name[11][i[2]] = gameCharacter.GaoChuan_ZhiYe;      //know another student
		name[11][i[3]] = gameCharacter.XiaoNiaoYou_Zhang;   //know another student
		name[11][i[4]] = gameCharacter.ZhongSi;             //teacher
		careerhash.put(Career.student, name[11]);
		name[12][i[0]] = gameCharacter.GaoYuan_YiChen;      //know another student
		name[12][i[1]] = gameCharacter.YuQi_ShaoTai;        //know another student
		name[12][i[2]] = gameCharacter.LianJian_Jing;       //know another teacher
		name[12][i[3]] = gameCharacter.QianCun_JiQing;     //know another teacher
		name[12][i[4]] = gameCharacter.ZhongSi;             //teacher
		careerhash.put(Career.teacher, name[12]);
		name[13][i[0]] = gameCharacter.Heilai_Lu;           //know another staff
		name[13][i[1]] = gameCharacter.ShouYe;              //know another staff
		name[13][i[2]] = gameCharacter.LongCheng;           //know another staff
		name[13][i[3]] = gameCharacter.XiaoYeSi_Lv;   //eat in my restaurant
		name[13][i[4]] = gameCharacter.ZhongSi;             //know another staff
		careerhash.put(Career.staff, name[13]);
		name[14][i[0]] = gameCharacter.XiaoYeSi_Lv;           //know editor
		name[14][i[1]] = gameCharacter.GaoYe_ZongZheng;       //know editor
		name[14][i[2]] = gameCharacter.ShengHu;      //collect info
		name[14][i[3]] = gameCharacter.QuanShui;   //collect info
		name[14][i[4]] = gameCharacter.YiTiao_LongMa;             //collect info
		careerhash.put(Career.writer, name[14]);
	}

	private int[] getRandomIndex(int length) {
		int [] defaultArr = new int[length];
		for(int i = 0; i<defaultArr.length; i++){
			defaultArr[i] = i;
		}
		int [] array = new int[length];
		int count = length;
		int randCount = 0, position = 0;
		int j=0;
		do{
			rand = new Random();
			int r = count - randCount;
			position = rand.nextInt(r);
			array[j++] = defaultArr[position];
			randCount++;
			defaultArr[position] = defaultArr[r-1];
		} while (randCount < count);
		return array;
	}
	
	public void getRandomPlayer(){
		rand = new Random();
		int playerNameIndex = rand.nextInt(gameCharacter.QuanShui.getIndex()+1); //TODO here is a hack. Need use a general way
		setPlayerName(gameCharacter.getName(playerNameIndex));
		setPlayerGValue(gameCharacter.getLevel(playerNameIndex));
		setPlayerCareer(gameCharacter.getCareer(playerNameIndex));
		setPlayerPhoto(gameCharacter.getPicture(playerNameIndex));
	}

	public void setPlayerName(String name){
		this.playerName = name;
	}
	public String getPlayerName(){
		return this.playerName;
	}
	
	public void setPlayerPhoto(int playerPhoto){
		this.playerPhoto = playerPhoto;
	}
	
	public int getPlayerPhoto(){
		return this.playerPhoto;
	}
	
	public void setPlayerGValue(int value){
		this.playerGValue = value;
	}
	
	public int getPlayerGValue(){
		return this.playerGValue;
	}
	
	public void setPlayerCareer(Career career){
		this.playerCareer = career;
	}
	public Career getPlayerCareer(){
		return this.playerCareer;
	}
	
	public void setMapEventNumber(int[] numbers){
		this.startNumber = numbers[0];
		this.makeFriendNumber = numbers[1];
		this.changeCareerNumber = numbers[2];
		this.dateNumber = numbers[3];
		this.luckyDrawNumber = numbers[4];
		this.moveForwardNumber = numbers[5];
		this.moveBackNumber = numbers[6];
		this.taskNumber = numbers[7];
		this.endNumber = numbers[8];
	}
	
	public int getStartNumber(){
		return startNumber;
	}
	
	public int getMakeFriendNumber(){
		return makeFriendNumber;
	}
	
	public int getChangeCareerNumber(){
		return changeCareerNumber;
	}
	
	public int getDateNumber(){
		return dateNumber;
	}
	
	public int getLuckyDrawNumber(){
		return luckyDrawNumber;
	}
	
	public int getMoveForwardNumber(){
		return moveForwardNumber;
	}
	
	public int getMoveBackNumber(){
		return moveBackNumber;
	}
	
	public int getTaskNumber(){
		return taskNumber;
	}
	
	public int getEndNumber(){
		return endNumber;
	}
	
	public gameCharacter appendKnownList(){

		gameCharacter nameList[] = new gameCharacter[KNOWN_MAX]; 
		nameList = careerhash.get(getPlayerCareer());
		Log.d("alice", "the Player career is " + getPlayerCareer());
		//while (nameList[nameIndex].getName() == getPlayerName()){
		//	nameIndex++;
		//}
		int nameIndex = 0;
		for(; nameIndex < KNOWN_MAX; nameIndex++){
			Log.d("alice", "nameList name is " + nameList[nameIndex].getName());
			Log.d("alice", "player name is " + getPlayerName());
			if(nameList[nameIndex].getName() == getPlayerName()){
				continue;
			}
			boolean found = false;
			for(gameCharacter known: knownList){
				if(known.getName() == nameList[nameIndex].getName()){
					found = true;
				}
			}
			if(found) continue;
			else break;
		}
		if(nameIndex < KNOWN_MAX){
		  knownList.add(nameList[nameIndex]);
		 // nameList[nameIndex].setFavor(StoryFriend.getFavorLevel(null, nameList[nameIndex].getName()));
		  return nameList[nameIndex];
		} else {
		  return null;
		}
	}
	
	public ArrayList<gameCharacter> getKnownList(){
		return knownList;
	}
	
	public void changeCareer(){
		rand = new Random();
		int careerIndex;
		do {
		  careerIndex = rand.nextInt(Career.waiter.getIndex()+1); //TODO here is a hack. Need use a general way
		} while (Career.getCareer(careerIndex) == getPlayerCareer());
		setPlayerCareer(Career.getCareer(careerIndex));
	}

	public gameCharacter getAKnownGuy() {
		if(knownList.size() == 0) {
			return null;
		} else {
		    rand = new Random();
		    return knownList.get(rand.nextInt(knownList.size()));
		}
	}
}