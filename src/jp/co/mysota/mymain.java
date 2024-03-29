//このソースは、VstoneMagicによって自動生成されました。
//ソースの内容を書き換えた場合、VstoneMagicで開けなくなる場合があります。
package	jp.co.mysota;
import	main.main.GlobalVariable;
import	jp.vstone.RobotLib.*;
import	jp.vstone.sotatalk.*;
import	jp.vstone.sotatalk.SpeechRecog.*;
import	jp.vstone.network.*;
import	java.io.*;
import	java.util.*;
import	java.awt.Color;
import	java.time.*;
import	java.util.Random;

public class mymain
{

	public short net_loop;
	public String recvMesg;
	public boolean isAuto;
	public jp.co.mysota.behavior behavior;
	public boolean isDay;
	public int dayStatus;
	public String event_title;
	public String event_date;
	public String event_type;
	public CPlayWave cplay;
	public String speechRecogResult;
	public RecogResult recogresult;
	public String readText;
	public CRobotPose pose;
	public String time_string;
	public LocalDateTime localDateTime;
	public int getDateTimeElement;
	public void net_main()																								//@<BlockInfo>jp.vstone.block.func,1664,464,2432,464,False,9,socketでメッセージを受け取る@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.thread,1728,464,2368,464,False,8,スレッド@</BlockInfo>
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						
						
																														//@<OutputChild>
						{																											//@<BlockInfo>jp.vstone.block.tcpip.server.init,1792,464,2304,464,False,7,@</BlockInfo>
							TCPIPServer tcpipServer = new TCPIPServer((short)5001,(int)5000);
							
																																	//@<OutputChild>
							while(net_loop==0)																						//@<BlockInfo>jp.vstone.block.while,1856,464,2240,464,False,6,TRUE@</BlockInfo>
							{
						
						
																																	//@<OutputChild>
								try{																								//@<BlockInfo>jp.vstone.block.tcpip.server,1920,416,2176,416,False,5,@</BlockInfo>
									GlobalVariable.recvString = tcpipServer.waitRequest();
						
									if(GlobalVariable.recvString==null) GlobalVariable.recvString="";
									if(GlobalVariable.recvString.contentEquals((String)"cmd;end"))
									{
																																	//@<OutputChild>
										net_loop=(short)1;																				//@<BlockInfo>jp.vstone.block.calculater,1984,416,1984,416,False,2,@</BlockInfo>
																																		//@<EndOfBlock/>
										CRobotUtil.wait((int)2000);																		//@<BlockInfo>jp.vstone.block.wait,2080,416,2080,416,False,1,コメント@</BlockInfo>	@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else
									{
																																	//@<OutputChild>
										recvMesg=(String)GlobalVariable.recvString;														//@<BlockInfo>jp.vstone.block.calculater,1984,512,1984,512,False,4,@</BlockInfo>
																																		//@<EndOfBlock/>
										CRobotUtil.Log(getClass().getSimpleName(), (String)recvMesg);									//@<BlockInfo>jp.vstone.block.printlog,2048,512,2048,512,False,3,@</BlockInfo>	@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									
								} catch(Exception e) { }
																																	//@<EndOfBlock/>
																																	//@</OutputChild>
							}
																																	//@<EndOfBlock/>
																																	//@</OutputChild>
						
							
						}
																																	//@<EndOfBlock/>
																																		//@</OutputChild>

						
					} catch(Exception e) {
						CRobotUtil.Err("jp.vstone.block.thread","例外が発生しました。");
						e.printStackTrace();
					}
				}
			});
			th.start();
		}
																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void auto()																									//@<BlockInfo>jp.vstone.block.func,48,720,1424,720,False,39,コメント@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.thread,112,720,1344,720,False,38,スレッド@</BlockInfo>
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						
						
																														//@<OutputChild>
						while(net_loop==0)																							//@<BlockInfo>jp.vstone.block.while,176,720,1264,720,False,37,TRUE@</BlockInfo>
						{
						
						
																																	//@<OutputChild>
							pose = new CRobotPose();																				//@<BlockInfo>jp.vstone.block.pose,240,720,240,720,False,36,コメント@</BlockInfo>
							pose.SetPose(	new Byte[]{1,2,3,4,5,6,7,8},
											new Short[]{0,-900,0,900,0,0,0,0}
											);
							pose.SetTorque(	new Byte[]{1,2,3,4,5,6,7,8},
											new Short[]{100,100,100,100,100,100,100,100}
											);
							pose.SetLed(	new Byte[]{0,1,2,8,9,10,11,12,13},
											new Short[]{0,255,0,180,80,0,180,80,0}
											);
							GlobalVariable.motion.play(pose,500);
							CRobotUtil.wait(500);																					//@<EndOfBlock/>
							if(isAuto==GlobalVariable.TRUE)																			//@<BlockInfo>jp.vstone.block.if,304,672,1184,672,False,35,コメント@</BlockInfo>
							{
																																	//@<OutputChild>
								if(!GlobalVariable.sotawish.isPlayIdling()) GlobalVariable.sotawish.StartIdling();					//@<BlockInfo>jp.vstone.block.talk.idling2,400,656,1104,672,False,31,Idling開始@</BlockInfo>
								try{
						
						
																																	//@<OutputChild>
									recogresult = GlobalVariable.recog.getRecognitionwithAbort((int)60000);							//@<BlockInfo>jp.vstone.block.talk.speechrecog.score2,528,144,992,144,False,30,音声認識を行い、認識候補との完全一致で比較する。認識スコアが一番高い結果に分岐する。実際に認識された文字列はspeechRecogResultに代入される@</BlockInfo>
									speechRecogResult = recogresult.CheckBest(new String[]{
									 "こんにちは" ,  "何ができる" ,  "名前は" ,  "ばいばい" ,  "建物" ,  "イベント" ,  "ありがとう" ,  "男" ,  "女" ,  "トイレ" ,  "お手洗い" ,  "" , 
									},false);
									if(speechRecogResult == null) speechRecogResult = "";
						
									if(speechRecogResult.contains((String)"こんにちは"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.stop();																//@<BlockInfo>jp.vstone.block.talk.say,704,144,704,144,False,10,@</BlockInfo>
											if(cplay != null){
											    cplay.stop();
											}
											
											{
												String selectLang = "日本語";
											
												switch(selectLang){
													case "日本語":
														jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
														break;
													case "英語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
														break;
													case "中国語_簡体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
														break;
													case "中国語_繁体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
														break;
													case "韓国語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
														break;
													default:
														break;
												}
											}
											GlobalVariable.sotawish.Say((String)"こんにちは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"何ができる"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.stop();																//@<BlockInfo>jp.vstone.block.talk.say,704,240,704,240,False,11,@</BlockInfo>
											if(cplay != null){
											    cplay.stop();
											}
											
											{
												String selectLang = "日本語";
											
												switch(selectLang){
													case "日本語":
														jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
														break;
													case "英語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
														break;
													case "中国語_簡体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
														break;
													case "中国語_繁体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
														break;
													case "韓国語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
														break;
													default:
														break;
												}
											}
											GlobalVariable.sotawish.Say((String)"イベントの案内や、この建物の案内ができるよ",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"名前は"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.stop();																//@<BlockInfo>jp.vstone.block.talk.say,704,336,704,336,False,12,@</BlockInfo>
											if(cplay != null){
											    cplay.stop();
											}
											
											{
												String selectLang = "日本語";
											
												switch(selectLang){
													case "日本語":
														jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
														break;
													case "英語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
														break;
													case "中国語_簡体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
														break;
													case "中国語_繁体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
														break;
													case "韓国語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
														break;
													default:
														break;
												}
											}
											GlobalVariable.sotawish.Say((String)"僕はソータ、っていうよ。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"ばいばい"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.stop();																//@<BlockInfo>jp.vstone.block.talk.say,704,432,704,432,False,13,@</BlockInfo>
											if(cplay != null){
											    cplay.stop();
											}
											
											{
												String selectLang = "日本語";
											
												switch(selectLang){
													case "日本語":
														jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
														break;
													case "英語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
														break;
													case "中国語_簡体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
														break;
													case "中国語_繁体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
														break;
													case "韓国語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
														break;
													default:
														break;
												}
											}
											GlobalVariable.sotawish.Say((String)"ばいばーい！またきてね！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"建物"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.stop();																//@<BlockInfo>jp.vstone.block.talk.say,704,528,704,528,False,14,Sota1@</BlockInfo>
											if(cplay != null){
											    cplay.stop();
											}
											
											{
												String selectLang = "日本語";
											
												switch(selectLang){
													case "日本語":
														jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
														break;
													case "英語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
														break;
													case "中国語_簡体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
														break;
													case "中国語_繁体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
														break;
													case "韓国語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
														break;
													default:
														break;
												}
											}
											GlobalVariable.sotawish.Say((String)"学研奈良登美ヶ丘駅に向かうバスは正門を出てすぐ左側、JR祝園駅、近鉄新祝園駅に向かうバスは道路を挟んで向かい側のバス停でバスをお待ちください。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"イベント"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.stop();																//@<BlockInfo>jp.vstone.block.talk.say,704,624,704,624,False,15,水道橋Sota用　イベント名を取得@</BlockInfo>
											if(cplay != null){
											    cplay.stop();
											}
											
											{
												String selectLang = "日本語";
											
												switch(selectLang){
													case "日本語":
														jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
														break;
													case "英語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
														break;
													case "中国語_簡体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
														break;
													case "中国語_繁体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
														break;
													case "韓国語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
														break;
													default:
														break;
												}
											}
											GlobalVariable.sotawish.Say((String)event_title + event_date + event_type,MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"ありがとう"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.stop();																//@<BlockInfo>jp.vstone.block.talk.say,704,720,704,720,False,16,@</BlockInfo>
											if(cplay != null){
											    cplay.stop();
											}
											
											{
												String selectLang = "日本語";
											
												switch(selectLang){
													case "日本語":
														jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
														break;
													case "英語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
														break;
													case "中国語_簡体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
														break;
													case "中国語_繁体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
														break;
													case "韓国語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
														break;
													default:
														break;
												}
											}
											GlobalVariable.sotawish.Say((String)"えへへ、どういたしまして。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"男"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.stop();																//@<BlockInfo>jp.vstone.block.talk.say,704,816,704,816,False,17,男性用トイレ案内@</BlockInfo>
											if(cplay != null){
											    cplay.stop();
											}
											
											{
												String selectLang = "日本語";
											
												switch(selectLang){
													case "日本語":
														jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
														break;
													case "英語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
														break;
													case "中国語_簡体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
														break;
													case "中国語_繁体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
														break;
													case "韓国語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
														break;
													default:
														break;
												}
											}
											GlobalVariable.sotawish.Say((String)"場所の案内だね。任せてよ!男性用トイレはこのフロアの中央にある通路を進んで、右側にあります。ハンガーラックが目印だよ。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"女"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.stop();																//@<BlockInfo>jp.vstone.block.talk.say,704,912,704,912,True,18,女性用トイレ案内@</BlockInfo>
											if(cplay != null){
											    cplay.stop();
											}
											
											{
												String selectLang = "日本語";
											
												switch(selectLang){
													case "日本語":
														jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
														break;
													case "英語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
														break;
													case "中国語_簡体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
														break;
													case "中国語_繁体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
														break;
													case "韓国語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
														break;
													default:
														break;
												}
											}
											GlobalVariable.sotawish.Say((String)"場所の案内だね。任せてよ!女性用トイレはこのフロアの中央にある通路を進んで、左側にあります。ハンガーラックが目印だよ。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"トイレ"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.stop();																//@<BlockInfo>jp.vstone.block.talk.say,704,1008,704,1008,False,19,(共通)トイレ案内@</BlockInfo>
											if(cplay != null){
											    cplay.stop();
											}
											
											{
												String selectLang = "日本語";
											
												switch(selectLang){
													case "日本語":
														jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
														break;
													case "英語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
														break;
													case "中国語_簡体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
														break;
													case "中国語_繁体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
														break;
													case "韓国語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
														break;
													default:
														break;
												}
											}
											GlobalVariable.sotawish.Say((String)"場所の案内だね。任せてよ!　このフロアの中央にある通路を進むと、左手に女性用、右手に男性用のトイレがあります。ハンガーラックが目印だよ。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"お手洗い"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.stop();																//@<BlockInfo>jp.vstone.block.talk.say,704,1104,704,1104,False,138,(共通)トイレ案内@</BlockInfo>
											if(cplay != null){
											    cplay.stop();
											}
											
											{
												String selectLang = "日本語";
											
												switch(selectLang){
													case "日本語":
														jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
														break;
													case "英語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
														break;
													case "中国語_簡体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
														break;
													case "中国語_繁体字":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
														break;
													case "韓国語":
														jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
														break;
													default:
														break;
												}
											}
											GlobalVariable.sotawish.Say((String)"場所の案内だね。任せてよ!　このフロアの中央にある通路を進むと、左手に女性用、右手に男性用のトイレがあります。ハンガーラックが目印だよ。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											switch(GlobalVariable.random.nextInt((int)16))												//@<BlockInfo>jp.vstone.block.random,608,1504,912,1488,True,29,何か音が聞こえたとき 1/5 (20%) の確率でランダムな定型文を発話する@</BlockInfo>
											{
												case (int)0:
												{
																																		//@<OutputChild>
													dayOrNight();																			//@<BlockInfo>jp.vstone.block.callfunc.base,624,1344,624,1344,False,24,@</BlockInfo>	@<EndOfBlock/>
													if(dayStatus==0)																		//@<BlockInfo>jp.vstone.block.if,704,1248,832,1248,False,23,コメント@</BlockInfo>
													{
																																			//@<OutputChild>
														GlobalVariable.sotawish.stop();														//@<BlockInfo>jp.vstone.block.talk.say,768,1248,768,1248,False,20,@</BlockInfo>
														if(cplay != null){
														    cplay.stop();
														}
													
														{
															String selectLang = "日本語";
													
															switch(selectLang){
																case "日本語":
																	jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
																	break;
																case "英語":
																	jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
																	break;
																case "中国語_簡体字":
																	jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
																	break;
																case "中国語_繁体字":
																	jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
																	break;
																case "韓国語":
																	jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
																	break;
																default:
																	break;
															}
														}
														GlobalVariable.sotawish.Say((String)"おはようございます。ようこそスタートアップサイドへ！！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																			//@<EndOfBlock/>
																																			//@</OutputChild>
													}
													else if(dayStatus==1)
													{
																																			//@<OutputChild>
														GlobalVariable.sotawish.stop();														//@<BlockInfo>jp.vstone.block.talk.say,768,1344,768,1344,False,21,@</BlockInfo>
														if(cplay != null){
														    cplay.stop();
														}
													
														{
															String selectLang = "日本語";
													
															switch(selectLang){
																case "日本語":
																	jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
																	break;
																case "英語":
																	jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
																	break;
																case "中国語_簡体字":
																	jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
																	break;
																case "中国語_繁体字":
																	jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
																	break;
																case "韓国語":
																	jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
																	break;
																default:
																	break;
															}
														}
														GlobalVariable.sotawish.Say((String)"こんにちは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																			//@<EndOfBlock/>
																																			//@</OutputChild>
													}
													else if(dayStatus==2)
													{
																																			//@<OutputChild>
														GlobalVariable.sotawish.stop();														//@<BlockInfo>jp.vstone.block.talk.say,768,1440,768,1440,False,22,@</BlockInfo>
														if(cplay != null){
														    cplay.stop();
														}
													
														{
															String selectLang = "日本語";
													
															switch(selectLang){
																case "日本語":
																	jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
																	break;
																case "英語":
																	jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
																	break;
																case "中国語_簡体字":
																	jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
																	break;
																case "中国語_繁体字":
																	jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
																	break;
																case "韓国語":
																	jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
																	break;
																default:
																	break;
															}
														}
														GlobalVariable.sotawish.Say((String)"こんばんは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																			//@<EndOfBlock/>
																																			//@</OutputChild>
													}
																																			//@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
												case (int)1:
												{
																																		//@<OutputChild>
													GlobalVariable.sotawish.stop();															//@<BlockInfo>jp.vstone.block.talk.say,736,1552,736,1552,False,25,@</BlockInfo>
													if(cplay != null){
													    cplay.stop();
													}
													
													{
														String selectLang = "日本語";
													
														switch(selectLang){
															case "日本語":
																jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
																break;
															case "英語":
																jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
																break;
															case "中国語_簡体字":
																jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
																break;
															case "中国語_繁体字":
																jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
																break;
															case "韓国語":
																jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
																break;
															default:
																break;
														}
													}
													GlobalVariable.sotawish.Say((String)"お仕事、お疲れさまです！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																			//@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
												case (int)2:
												{
																																		//@<OutputChild>
													GlobalVariable.sotawish.stop();															//@<BlockInfo>jp.vstone.block.talk.say,736,1664,736,1664,False,26,@</BlockInfo>
													if(cplay != null){
													    cplay.stop();
													}
													
													{
														String selectLang = "日本語";
													
														switch(selectLang){
															case "日本語":
																jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
																break;
															case "英語":
																jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
																break;
															case "中国語_簡体字":
																jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
																break;
															case "中国語_繁体字":
																jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
																break;
															case "韓国語":
																jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
																break;
															default:
																break;
														}
													}
													GlobalVariable.sotawish.Say((String)"今、受付のお手伝いをしています！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																			//@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
												case (int)3:
												{
																																		//@<OutputChild>
													GlobalVariable.sotawish.stop();															//@<BlockInfo>jp.vstone.block.talk.say,736,1760,736,1760,False,27,@</BlockInfo>
													if(cplay != null){
													    cplay.stop();
													}
													
													{
														String selectLang = "日本語";
													
														switch(selectLang){
															case "日本語":
																jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
																break;
															case "英語":
																jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
																break;
															case "中国語_簡体字":
																jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
																break;
															case "中国語_繁体字":
																jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
																break;
															case "韓国語":
																jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
																break;
															default:
																break;
														}
													}
													GlobalVariable.sotawish.Say((String)"何かわからないことがあれば、話しかけてね！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																			//@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
												default:
												{
																																		//@<OutputChild>
													CRobotUtil.Log(getClass().getSimpleName(), (String)"発話を見送るよ。");							//@<BlockInfo>jp.vstone.block.printlog,736,1872,736,1872,False,28,@</BlockInfo>	@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
											
											}																							//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
																																	//@<EndOfBlock/>
																																	//@</OutputChild>
						
						
								}
								finally
								{
									GlobalVariable.sotawish.StopIdling();
								}
																																	//@<EndOfBlock/>
																																	//@</OutputChild>
							}
							else
							{
																																	//@<OutputChild>
								if(!GlobalVariable.sotawish.isPlayIdling()) GlobalVariable.sotawish.StartIdling();					//@<BlockInfo>jp.vstone.block.talk.idling2,400,1968,1104,1968,False,34,Idling開始@</BlockInfo>
								try{
						
						
																																	//@<OutputChild>
									CRobotUtil.Log(getClass().getSimpleName(), (String)"auto状態待機");									//@<BlockInfo>jp.vstone.block.printlog,464,1968,464,1968,False,33,@</BlockInfo>	@<EndOfBlock/>
									CRobotUtil.wait((int)100);																		//@<BlockInfo>jp.vstone.block.wait,1040,1968,1040,1968,False,32,コメント@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						
						
								}
								finally
								{
									GlobalVariable.sotawish.StopIdling();
								}
																																	//@<EndOfBlock/>
																																	//@</OutputChild>
							}
																																	//@<EndOfBlock/>
																																	//@</OutputChild>
						}
																																	//@<EndOfBlock/>
																																		//@</OutputChild>

						
					} catch(Exception e) {
						CRobotUtil.Err("jp.vstone.block.thread","例外が発生しました。");
						e.printStackTrace();
					}
				}
			});
			th.start();
		}
																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public mymain()																										//@<BlockInfo>jp.vstone.block.func.constructor,48,32,1296,32,False,57,@</BlockInfo>
	{
																														//@<OutputChild>
		net_loop=0;																										//@<BlockInfo>jp.vstone.block.variable,112,32,112,32,False,56,break@</BlockInfo>
																														//@<EndOfBlock/>
		recvMesg="";																									//@<BlockInfo>jp.vstone.block.variable,176,32,176,32,False,55,break@</BlockInfo>
																														//@<EndOfBlock/>
		isAuto=true;																									//@<BlockInfo>jp.vstone.block.variable,240,32,240,32,False,54,break@</BlockInfo>
																														//@<EndOfBlock/>
		behavior=new jp.co.mysota.behavior();																			//@<BlockInfo>jp.vstone.block.variable,304,32,304,32,False,53,break@</BlockInfo>
																														//@<EndOfBlock/>
		isDay=true;																										//@<BlockInfo>jp.vstone.block.variable,368,32,368,32,False,52,break@</BlockInfo>
																														//@<EndOfBlock/>
		dayStatus=0;																									//@<BlockInfo>jp.vstone.block.variable,432,32,432,32,False,51,break@</BlockInfo>
																														//@<EndOfBlock/>
		event_title="ホームページに掲載しているよ。ぜひ見てみてね。";																			//@<BlockInfo>jp.vstone.block.variable,496,32,496,32,False,50,break@</BlockInfo>
																														//@<EndOfBlock/>
		event_date=" ";																									//@<BlockInfo>jp.vstone.block.variable,560,32,560,32,False,49,break@</BlockInfo>
																														//@<EndOfBlock/>
		event_type=" ";																									//@<BlockInfo>jp.vstone.block.variable,624,32,624,32,False,48,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*CPlayWave cplay*/;																							//@<BlockInfo>jp.vstone.block.variable,784,32,784,32,False,47,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*String speechRecogResult*/;																					//@<BlockInfo>jp.vstone.block.variable,848,32,848,32,False,46,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*RecogResult recogresult*/;																					//@<BlockInfo>jp.vstone.block.variable,912,32,912,32,False,45,break@</BlockInfo>
																														//@<EndOfBlock/>
		readText="";																									//@<BlockInfo>jp.vstone.block.variable,976,32,976,32,False,44,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*CRobotPose pose*/;																							//@<BlockInfo>jp.vstone.block.variable,1040,32,1040,32,False,43,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*String time_string*/;																							//@<BlockInfo>jp.vstone.block.variable,1104,32,1104,32,False,42,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*LocalDateTime localDateTime*/;																				//@<BlockInfo>jp.vstone.block.variable,1168,32,1168,32,False,41,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*int getDateTimeElement*/;																						//@<BlockInfo>jp.vstone.block.variable,1232,32,1232,32,False,40,break@</BlockInfo>
																														//@<EndOfBlock/>
																														//@</OutputChild>
	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void dayOrNight()																							//@<BlockInfo>jp.vstone.block.func,2576,512,2992,512,False,63,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		localDateTime = LocalDateTime.now();																			//@<BlockInfo>jp.vstone.block.time.getlocaldatetime,2640,512,2640,512,False,62,ローカル時間をロボット内のカレンダーより取得し、変数LocalDateTime lodalDateTimeに代入。取得した情報から日時などを個別に切り出す場合は「日時の切り出しブロック」を使う@</BlockInfo>
																														//@<EndOfBlock/>
		{																												//@<BlockInfo>jp.vstone.block.time.getdatetimeelement,2704,512,2704,512,False,61,dateTimeに記録された日時情報から、年・月・日・時・分・秒のうち一つをtypeで指定し、変数int getDateTimeElementに代入する@</BlockInfo>
			LocalDateTime d = (LocalDateTime)localDateTime;
			getDateTimeElement = d.getHour();
		}																												//@<EndOfBlock/>
		if(getDateTimeElement>=0 && getDateTimeElement<5){																//@<BlockInfo>jp.vstone.block.freeproc,2784,512,2784,512,False,60,@</BlockInfo>
			isDay = false;
			dayStatus = 2;
			System.out.println("夜です" + getDateTimeElement);
		}
		else if(getDateTimeElement >=5 && getDateTimeElement <10){
			isDay = true;
			dayStatus = 0;
			System.out.println("朝です" + getDateTimeElement);
		}
		else if(getDateTimeElement >=10 && getDateTimeElement <17){
			isDay = true;
			dayStatus = 1;
			System.out.println("昼です" + getDateTimeElement);
		}
		else if(getDateTimeElement>=17 && getDateTimeElement<24){
			isDay = false;
			dayStatus = 2;
			System.out.println("夜です" + getDateTimeElement);
		}
																														//@<EndOfBlock/>
		CRobotUtil.Log(getClass().getSimpleName(), (String)"現在の時刻（Hour）は....");											//@<BlockInfo>jp.vstone.block.printlog,2848,512,2848,512,False,59,@</BlockInfo>	@<EndOfBlock/>
		CRobotUtil.Log(getClass().getSimpleName(), (String)time_string);												//@<BlockInfo>jp.vstone.block.printlog,2912,512,2912,512,False,58,@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void main()																									//@<BlockInfo>jp.vstone.block.func,1664,240,2544,240,False,74,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		GlobalVariable.sotawish.stop();																					//@<BlockInfo>jp.vstone.block.talk.say,1728,240,1728,240,False,73,@</BlockInfo>
		if(cplay != null){
		    cplay.stop();
		}

		{
			String selectLang = "日本語";

			switch(selectLang){
				case "日本語":
					jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
					break;
				case "英語":
					jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
					break;
				case "中国語_簡体字":
					jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
					break;
				case "中国語_繁体字":
					jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
					break;
				case "韓国語":
					jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
					break;
				default:
					break;
			}
		}
		GlobalVariable.sotawish.Say((String)"こんにちは。ようこそエーティーアールへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																														//@<EndOfBlock/>
		net_main();																										//@<BlockInfo>jp.vstone.block.callfunc.base,1808,240,1808,240,False,72,@</BlockInfo>	@<EndOfBlock/>
		auto();																											//@<BlockInfo>jp.vstone.block.callfunc.base,1872,240,1872,240,False,71,@</BlockInfo>	@<EndOfBlock/>
		if(!GlobalVariable.sotawish.isPlayIdling()) GlobalVariable.sotawish.StartIdling();								//@<BlockInfo>jp.vstone.block.talk.idling2,1936,240,2480,240,False,70,Idling開始@</BlockInfo>
		try{


																														//@<OutputChild>
			while(net_loop==0)																							//@<BlockInfo>jp.vstone.block.while,2000,240,2416,240,False,69,TRUE@</BlockInfo>
			{


																														//@<OutputChild>
				if(recvMesg!="")																						//@<BlockInfo>jp.vstone.block.if,2064,192,2352,192,False,68,コメント@</BlockInfo>
				{
																														//@<OutputChild>
					String argString=recvMesg;																			//@<BlockInfo>jp.vstone.block.variable,2128,192,2128,192,False,66,break@</BlockInfo>
																														//@<EndOfBlock/>
					recvMesg=(String)"";																				//@<BlockInfo>jp.vstone.block.calculater,2208,192,2208,192,False,65,@</BlockInfo>
																														//@<EndOfBlock/>
					teleop((String)argString);																			//@<BlockInfo>jp.vstone.block.callfunc.base,2288,192,2288,192,False,64,@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>
				}
				else
				{
																														//@<OutputChild>
					CRobotUtil.wait((int)100);																			//@<BlockInfo>jp.vstone.block.wait,2208,288,2208,288,False,67,コメント@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>
				}
																														//@<EndOfBlock/>
																														//@</OutputChild>
			}
																														//@<EndOfBlock/>
																														//@</OutputChild>


		}
		finally
		{
			GlobalVariable.sotawish.StopIdling();
		}
																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void teleop(String cmdMesg)																					//@<BlockInfo>jp.vstone.block.func,1664,784,2592,784,False,90,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		String[] cmdArray=cmdMesg.split(";",-1);																		//@<BlockInfo>jp.vstone.block.variable,1728,784,1728,784,False,89,break@</BlockInfo>
																														//@<EndOfBlock/>
		boolean isCmd=false;																							//@<BlockInfo>jp.vstone.block.variable,1792,784,1792,784,False,88,break@</BlockInfo>
																														//@<EndOfBlock/>
		boolean isMotion=false;																							//@<BlockInfo>jp.vstone.block.variable,1856,784,1856,784,False,87,break@</BlockInfo>
																														//@<EndOfBlock/>
		boolean isScenario=false;																						//@<BlockInfo>jp.vstone.block.variable,1920,784,1920,784,False,86,break@</BlockInfo>
																														//@<EndOfBlock/>
		for(int i=0;i<(int)cmdArray.length;i++)																			//@<BlockInfo>jp.vstone.block.for,1984,784,2528,784,False,85,コメント@</BlockInfo>
		{
																														//@<OutputChild>
			CRobotUtil.Log(getClass().getSimpleName(), (String)"検索対象" + cmdArray[i]);									//@<BlockInfo>jp.vstone.block.printlog,2048,784,2048,784,False,84,@</BlockInfo>	@<EndOfBlock/>
			{																											//@<BlockInfo>jp.vstone.block.text.regex,2112,640,2400,640,False,83,textに設定した文字列を正規表現でマッチングする。@</BlockInfo>
				String text = (String)cmdArray[i];
				if(text.matches((String)"cmd"))
				{
				
																														//@<OutputChild>
					isCmd=(boolean)true;																					//@<BlockInfo>jp.vstone.block.calculater,2256,640,2256,640,False,75,@</BlockInfo>
																															//@<EndOfBlock/>
																																//@</OutputChild>

				}
				else if(text.matches((String)"motion"))
				{
				
																														//@<OutputChild>
					isMotion=(boolean)true;																					//@<BlockInfo>jp.vstone.block.calculater,2192,736,2192,736,False,78,@</BlockInfo>
																															//@<EndOfBlock/>
					doMotion((String)cmdArray[i+1]);																		//@<BlockInfo>jp.vstone.block.callfunc.base,2256,736,2256,736,False,77,@</BlockInfo>	@<EndOfBlock/>
					i=(int)i + 1;																							//@<BlockInfo>jp.vstone.block.calculater,2320,736,2320,736,False,76,@</BlockInfo>
																															//@<EndOfBlock/>
																																//@</OutputChild>

				}
				else if(text.matches((String)"scenario"))
				{
				
																														//@<OutputChild>
					isScenario=true;																						//@<BlockInfo>jp.vstone.block.calculater,2192,832,2192,832,False,81,@</BlockInfo>
																															//@<EndOfBlock/>
					doScenario((String)cmdArray[i+1]);																		//@<BlockInfo>jp.vstone.block.callfunc.base,2256,832,2256,832,False,80,@</BlockInfo>	@<EndOfBlock/>
					i=(int)i + 1;																							//@<BlockInfo>jp.vstone.block.calculater,2320,832,2320,832,False,79,@</BlockInfo>
																															//@<EndOfBlock/>
																																//@</OutputChild>

				}
				else
				{
				
																														//@<OutputChild>
																														//@</OutputChild>

				}
				
			}																											//@<EndOfBlock/>
			if(isCmd==false && i==0){																					//@<BlockInfo>jp.vstone.block.freeproc,2464,784,2464,784,False,82,@</BlockInfo>
				System.out.println("第一コマンドが間違っています");
				break;
			}
			else if(isMotion==false && i==1){
				System.out.println("第二コマンドが間違っています");
				break;
			}
																														//@<EndOfBlock/>
																														//@</OutputChild>
		}																												//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void doMotion(String strMotionName)																			//@<BlockInfo>jp.vstone.block.func,1632,1488,2288,1488,False,115,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.talk.idling.stop,1696,1488,2224,1488,False,114,Idling動作を一時的に無効にします@</BlockInfo>
			boolean isIdling = GlobalVariable.sotawish.isPlayIdling();
			if(isIdling) GlobalVariable.sotawish.StopIdling();
			{


																														//@<OutputChild>
				pose = new CRobotPose();																					//@<BlockInfo>jp.vstone.block.pose,1760,1488,1760,1488,False,113,コメント@</BlockInfo>
				pose.SetPose(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{0,-900,0,900,0,0,0,0}
								);
				pose.SetTorque(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{100,100,100,100,100,100,100,100}
								);
				pose.SetLed(	new Byte[]{0,1,2,8,9,10,11,12,13},
								new Short[]{0,255,0,180,80,0,180,80,0}
								);
				GlobalVariable.motion.play(pose,1000);
				CRobotUtil.wait(1000);																						//@<EndOfBlock/>
				switch((String)strMotionName)																				//@<BlockInfo>jp.vstone.block.switch,1856,1056,2144,1056,False,112,@</BlockInfo>
				{
					case (String)"Nodding":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"HandNodding ! -- うなずきます");							//@<BlockInfo>jp.vstone.block.printlog,1968,1056,1968,1056,False,92,@</BlockInfo>	@<EndOfBlock/>
						behavior.head_nodding();																				//@<BlockInfo>jp.vstone.block.callfunc.base,2032,1056,2032,1056,False,91,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"HeadShaking":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"HandShaking ! -- いやいやします");							//@<BlockInfo>jp.vstone.block.printlog,1968,1152,1968,1152,False,94,@</BlockInfo>	@<EndOfBlock/>
						behavior.head_shaking();																				//@<BlockInfo>jp.vstone.block.callfunc.base,2032,1152,2032,1152,False,93,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"LeftHandWaving":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"LeftHandWaving !");									//@<BlockInfo>jp.vstone.block.printlog,1968,1248,1968,1248,False,96,@</BlockInfo>	@<EndOfBlock/>
						behavior.lefthand_waving();																				//@<BlockInfo>jp.vstone.block.callfunc.base,2032,1248,2032,1248,False,95,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"RightHandWaving":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"RightHandWaving ! -- 右手を振ります");						//@<BlockInfo>jp.vstone.block.printlog,1968,1344,1968,1344,False,98,@</BlockInfo>	@<EndOfBlock/>
						behavior.righthand_waving();																			//@<BlockInfo>jp.vstone.block.callfunc.base,2032,1344,2032,1344,False,97,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"BothHandWaving":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"BothHandWaving ! -- 両手を振ります");						//@<BlockInfo>jp.vstone.block.printlog,1968,1440,1968,1440,False,100,@</BlockInfo>	@<EndOfBlock/>
						behavior.bothhand_waving();																				//@<BlockInfo>jp.vstone.block.callfunc.base,2032,1440,2032,1440,False,99,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"BothHandRaising":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"BothHandRaising ! -- 両手を上下します");					//@<BlockInfo>jp.vstone.block.printlog,1968,1536,1968,1536,False,102,@</BlockInfo>	@<EndOfBlock/>
						behavior.bothhand_raising();																			//@<BlockInfo>jp.vstone.block.callfunc.base,2032,1536,2032,1536,False,101,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"AutoStart":
					{
																															//@<OutputChild>
						isAuto=(boolean)true;																					//@<BlockInfo>jp.vstone.block.calculater,1936,1632,1936,1632,False,105,@</BlockInfo>
																																//@<EndOfBlock/>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"AutoStart ! -- 自動応答を始めます");							//@<BlockInfo>jp.vstone.block.printlog,2000,1632,2000,1632,False,104,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.stop();																			//@<BlockInfo>jp.vstone.block.talk.say,2064,1632,2064,1632,False,103,@</BlockInfo>
						if(cplay != null){
						    cplay.stop();
						}
						
						{
							String selectLang = "日本語";
						
							switch(selectLang){
								case "日本語":
									jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
									break;
								case "英語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
									break;
								case "中国語_簡体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
									break;
								case "中国語_繁体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
									break;
								case "韓国語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
									break;
								default:
									break;
							}
						}
						GlobalVariable.sotawish.Say((String)"オペレータさん、お疲れ様！ここからは僕に任せて！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"AutoStop":
					{
																															//@<OutputChild>
						isAuto=(boolean)false;																					//@<BlockInfo>jp.vstone.block.calculater,1936,1728,1936,1728,False,108,@</BlockInfo>
																																//@<EndOfBlock/>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"AutoStop ! -- 自動応答を終了します");							//@<BlockInfo>jp.vstone.block.printlog,2000,1728,2000,1728,False,107,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.stop();																			//@<BlockInfo>jp.vstone.block.talk.say,2064,1728,2064,1728,False,106,@</BlockInfo>
						if(cplay != null){
						    cplay.stop();
						}
						
						{
							String selectLang = "日本語";
						
							switch(selectLang){
								case "日本語":
									jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
									break;
								case "英語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
									break;
								case "中国語_簡体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
									break;
								case "中国語_繁体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
									break;
								case "韓国語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
									break;
								default:
									break;
							}
						}
						GlobalVariable.sotawish.Say((String)"オペレータさんが来てくれたよ！バトンタッチするね！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"auto_response":
					{
																															//@<OutputChild>
						isAuto=(boolean)false;																					//@<BlockInfo>jp.vstone.block.calculater,1968,1824,1968,1824,False,110,@</BlockInfo>
																																//@<EndOfBlock/>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"auto_response ! -- 自動応答を終了します");					//@<BlockInfo>jp.vstone.block.printlog,2032,1824,2032,1824,False,109,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					default:
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"それは登録されてないわー");										//@<BlockInfo>jp.vstone.block.printlog,1984,1920,1984,1920,False,111,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
				
				}																											//@<EndOfBlock/>
																																//@</OutputChild>


			}
			if(isIdling) GlobalVariable.sotawish.StartIdling();
		}																												//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void doScenario(String strMotionName)																		//@<BlockInfo>jp.vstone.block.func,2432,1344,3408,1344,False,137,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.talk.idling.stop,2496,1344,3328,1344,False,136,Idling動作を一時的に無効にします@</BlockInfo>
			boolean isIdling = GlobalVariable.sotawish.isPlayIdling();
			if(isIdling) GlobalVariable.sotawish.StopIdling();
			{


																														//@<OutputChild>
				pose = new CRobotPose();																					//@<BlockInfo>jp.vstone.block.pose,2560,1344,2560,1344,False,135,コメント@</BlockInfo>
				pose.SetPose(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{0,-900,0,900,0,0,0,0}
								);
				pose.SetTorque(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{100,100,100,100,100,100,100,100}
								);
				pose.SetLed(	new Byte[]{0,1,2,8,9,10,11,12,13},
								new Short[]{0,255,0,180,80,0,180,80,0}
								);
				GlobalVariable.motion.play(pose,500);
				CRobotUtil.wait(500);																						//@<EndOfBlock/>
				switch((String)strMotionName)																				//@<BlockInfo>jp.vstone.block.switch,2640,1056,3248,1056,False,134,@</BlockInfo>
				{
					case (String)"Greeting":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"Greeting ! -- 挨拶します");								//@<BlockInfo>jp.vstone.block.printlog,2736,1056,2736,1056,False,117,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.stop();																			//@<BlockInfo>jp.vstone.block.talk.say,2800,1056,2800,1056,False,116,@</BlockInfo>
						if(cplay != null){
						    cplay.stop();
						}
						
						{
							String selectLang = "日本語";
						
							switch(selectLang){
								case "日本語":
									jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
									break;
								case "英語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
									break;
								case "中国語_簡体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
									break;
								case "中国語_繁体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
									break;
								case "韓国語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
									break;
								default:
									break;
							}
						}
						GlobalVariable.sotawish.Say((String)"こんにちは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"Selfintroduction":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"Introduction ! -- 自己紹介します");						//@<BlockInfo>jp.vstone.block.printlog,2736,1152,2736,1152,False,119,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.stop();																			//@<BlockInfo>jp.vstone.block.talk.say,2800,1152,2800,1152,False,118,@</BlockInfo>
						if(cplay != null){
						    cplay.stop();
						}
						
						{
							String selectLang = "日本語";
						
							switch(selectLang){
								case "日本語":
									jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
									break;
								case "英語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
									break;
								case "中国語_簡体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
									break;
								case "中国語_繁体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
									break;
								case "韓国語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
									break;
								default:
									break;
							}
						}
						GlobalVariable.sotawish.Say((String)"僕は、ソータっていうよ。この建物の案内をしているんだ。何か聞きたいことはあるかな？",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"VenueInfo":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"VenueInfo ! -- 建物の案内をします");							//@<BlockInfo>jp.vstone.block.printlog,2736,1248,2736,1248,False,121,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.stop();																			//@<BlockInfo>jp.vstone.block.talk.say,2800,1248,2800,1248,False,120,@</BlockInfo>
						if(cplay != null){
						    cplay.stop();
						}
						
						{
							String selectLang = "日本語";
						
							switch(selectLang){
								case "日本語":
									jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
									break;
								case "英語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
									break;
								case "中国語_簡体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
									break;
								case "中国語_繁体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
									break;
								case "韓国語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
									break;
								default:
									break;
							}
						}
						GlobalVariable.sotawish.Say((String)"建物の案内だね、任せてよ、。　9階はコワーキングスペースです。。。7階と8階には、会議室と、プライベートオフィスや、オンラインブースがあるよ。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"EventInfo":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"EventInfo ! -- イベント情報を発話します");						//@<BlockInfo>jp.vstone.block.printlog,2736,1344,2736,1344,False,128,@</BlockInfo>	@<EndOfBlock/>
						String[] info=GlobalVariable.recvString.split(";", -1);													//@<BlockInfo>jp.vstone.block.variable,2800,1344,2800,1344,False,127,break@</BlockInfo>
																																//@<EndOfBlock/>
						event_title=(String)"直近のイベントを紹介します。" + info[3] + "が、";													//@<BlockInfo>jp.vstone.block.calculater,2864,1344,2864,1344,False,126,@</BlockInfo>
																																//@<EndOfBlock/>
						event_title=(String)"直近のイベントを紹介します。" + info[3] + "が、";													//@<BlockInfo>jp.vstone.block.calculater,2928,1344,2928,1344,False,125,@</BlockInfo>
																																//@<EndOfBlock/>
						event_date=(String)info[4] + "予定です。";																	//@<BlockInfo>jp.vstone.block.calculater,2992,1344,2992,1344,False,124,@</BlockInfo>
																																//@<EndOfBlock/>
						event_type=(String)info[5] + "で開催するよ。ご参加をお待ちしております。";													//@<BlockInfo>jp.vstone.block.calculater,3056,1344,3056,1344,False,123,@</BlockInfo>
																																//@<EndOfBlock/>
						GlobalVariable.sotawish.stop();																			//@<BlockInfo>jp.vstone.block.talk.say,3152,1344,3152,1344,False,122,イベント名@</BlockInfo>
						if(cplay != null){
						    cplay.stop();
						}
						
						{
							String selectLang = "日本語";
						
							switch(selectLang){
								case "日本語":
									jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
									break;
								case "英語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
									break;
								case "中国語_簡体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
									break;
								case "中国語_繁体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
									break;
								case "韓国語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
									break;
								default:
									break;
							}
						}
						GlobalVariable.sotawish.Say((String)event_title + event_date + event_type,MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"Restroom":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"Restroom ! -- トイレ案内を発話します");						//@<BlockInfo>jp.vstone.block.printlog,2736,1440,2736,1440,False,130,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.stop();																			//@<BlockInfo>jp.vstone.block.talk.say,2800,1440,2800,1440,False,129,(共通)トイレ案内@</BlockInfo>
						if(cplay != null){
						    cplay.stop();
						}
						
						{
							String selectLang = "日本語";
						
							switch(selectLang){
								case "日本語":
									jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
									break;
								case "英語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
									break;
								case "中国語_簡体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
									break;
								case "中国語_繁体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
									break;
								case "韓国語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
									break;
								default:
									break;
							}
						}
						GlobalVariable.sotawish.Say((String)"場所の案内だね。任せてよ!　このフロアの中央にある通路を進むと、左手に女性用、右手に男性用のトイレがあります。ハンガーラックが目印だよ。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"OpBooth":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"OpBooth ! -- 遠隔操作ブースの情報を発話します");					//@<BlockInfo>jp.vstone.block.printlog,2736,1536,2736,1536,False,132,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.stop();																			//@<BlockInfo>jp.vstone.block.talk.say,2800,1536,2800,1536,False,131,操作ブース案内@</BlockInfo>
						if(cplay != null){
						    cplay.stop();
						}
						
						{
							String selectLang = "日本語";
						
							switch(selectLang){
								case "日本語":
									jp.vstone.sotatalk.TextToSpeechSota.cancelLocalize();
									break;
								case "英語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("en");
									break;
								case "中国語_簡体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-chs");
									break;
								case "中国語_繁体字":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("zh-cht");
									break;
								case "韓国語":
									jp.vstone.sotatalk.TextToSpeechSota.setLocalizeLang("ko");
									break;
								default:
									break;
							}
						}
						GlobalVariable.sotawish.Say((String)"それじゃあブースを紹介するよ！今、スタートアップサイドでは移動型アバターのテレコや、受付のソータといったアバターの遠隔操作を体験できるブースを設置しています。ぜひ操作体験をしてみてね！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					default:
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"それは登録されてないよ");										//@<BlockInfo>jp.vstone.block.printlog,2752,1632,2752,1632,False,133,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
				
				}																											//@<EndOfBlock/>
																																//@</OutputChild>


			}
			if(isIdling) GlobalVariable.sotawish.StartIdling();
		}																												//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

}
