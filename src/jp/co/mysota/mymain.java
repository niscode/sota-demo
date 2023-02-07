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
	public CPlayWave cplay;
	public String speechRecogResult;
	public RecogResult recogresult;
	public String readText;
	public CRobotPose pose;
	public String time_string;
	public LocalDateTime localDateTime;
	public int getDateTimeElement;
	public void doMotion(String strMotionName)																			//@<BlockInfo>jp.vstone.block.func,32,2336,688,2336,False,23,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.talk.idling.stop,96,2336,608,2336,False,22,Idling動作を一時的に無効にします@</BlockInfo>
			boolean isIdling = GlobalVariable.sotawish.isPlayIdling();
			if(isIdling) GlobalVariable.sotawish.StopIdling();
			{


																														//@<OutputChild>
				pose = new CRobotPose();																					//@<BlockInfo>jp.vstone.block.pose,160,2336,160,2336,False,21,コメント@</BlockInfo>
				pose.SetPose(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{0,-900,0,900,0,0,0,0}
								);
				pose.SetTorque(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{100,100,100,100,100,100,100,100}
								);
				pose.SetLed(	new Byte[]{0,1,2,8,9,10,11,12,13},
								new Short[]{0,-255,0,180,80,0,180,80,0}
								);
				GlobalVariable.motion.play(pose,800);
				CRobotUtil.wait(800);																						//@<EndOfBlock/>
				switch((String)strMotionName)																				//@<BlockInfo>jp.vstone.block.switch,240,2048,528,2048,False,20,@</BlockInfo>
				{
					case (String)"Nodding":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"HandNodding ! -- うなずきます");							//@<BlockInfo>jp.vstone.block.printlog,352,2048,352,2048,False,2,@</BlockInfo>	@<EndOfBlock/>
						behavior.head_nodding();																				//@<BlockInfo>jp.vstone.block.callfunc.base,416,2048,416,2048,False,1,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"HeadShaking":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"HandShaking ! -- いやいやします");							//@<BlockInfo>jp.vstone.block.printlog,352,2144,352,2144,False,4,@</BlockInfo>	@<EndOfBlock/>
						behavior.head_shaking();																				//@<BlockInfo>jp.vstone.block.callfunc.base,416,2144,416,2144,False,3,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"LeftHandWaving":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"LeftHandWaving !");									//@<BlockInfo>jp.vstone.block.printlog,352,2240,352,2240,False,6,@</BlockInfo>	@<EndOfBlock/>
						behavior.lefthand_waving();																				//@<BlockInfo>jp.vstone.block.callfunc.base,416,2240,416,2240,False,5,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"RightHandWaving":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"RightHandWaving ! -- 右手を振ります");						//@<BlockInfo>jp.vstone.block.printlog,352,2336,352,2336,False,8,@</BlockInfo>	@<EndOfBlock/>
						behavior.righthand_waving();																			//@<BlockInfo>jp.vstone.block.callfunc.base,416,2336,416,2336,False,7,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"BothHandWaving":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"BothHandWaving ! -- 両手を振ります");						//@<BlockInfo>jp.vstone.block.printlog,352,2432,352,2432,False,10,@</BlockInfo>	@<EndOfBlock/>
						behavior.bothhand_waving();																				//@<BlockInfo>jp.vstone.block.callfunc.base,416,2432,416,2432,False,9,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"BothHandRaising":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"BothHandRaising ! -- 両手を上下します");					//@<BlockInfo>jp.vstone.block.printlog,352,2528,352,2528,False,12,@</BlockInfo>	@<EndOfBlock/>
						behavior.bothhand_raising();																			//@<BlockInfo>jp.vstone.block.callfunc.base,416,2528,416,2528,False,11,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"AutoStart":
					{
																															//@<OutputChild>
						isAuto=(boolean)true;																					//@<BlockInfo>jp.vstone.block.calculater,320,2624,320,2624,False,15,@</BlockInfo>
																																//@<EndOfBlock/>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"AutoStart ! -- 自動応答を始めます");							//@<BlockInfo>jp.vstone.block.printlog,384,2624,384,2624,False,14,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"オペレータさん、お疲れ様！ここからは僕に任せて！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,448,2624,448,2624,False,13,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"AutoStop":
					{
																															//@<OutputChild>
						isAuto=(boolean)false;																					//@<BlockInfo>jp.vstone.block.calculater,320,2720,320,2720,False,18,@</BlockInfo>
																																//@<EndOfBlock/>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"AutoStop ! -- 自動応答を終了します");							//@<BlockInfo>jp.vstone.block.printlog,384,2720,384,2720,False,17,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"オペレータさんが来てくれたよ！バトンタッチするね！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,448,2720,448,2720,False,16,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					default:
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"それは登録されてないわー");										//@<BlockInfo>jp.vstone.block.printlog,384,2816,384,2816,False,19,@</BlockInfo>	@<EndOfBlock/>
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
	public void teleop(String cmdMesg)																					//@<BlockInfo>jp.vstone.block.func,48,736,976,736,False,39,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		String[] cmdArray=cmdMesg.split(";",-1);																		//@<BlockInfo>jp.vstone.block.variable,112,736,112,736,False,38,break@</BlockInfo>
																														//@<EndOfBlock/>
		boolean isCmd=false;																							//@<BlockInfo>jp.vstone.block.variable,176,736,176,736,False,37,break@</BlockInfo>
																														//@<EndOfBlock/>
		boolean isMotion=false;																							//@<BlockInfo>jp.vstone.block.variable,240,736,240,736,False,36,break@</BlockInfo>
																														//@<EndOfBlock/>
		boolean isScenario=false;																						//@<BlockInfo>jp.vstone.block.variable,304,736,304,736,False,35,break@</BlockInfo>
																														//@<EndOfBlock/>
		for(int i=0;i<(int)cmdArray.length;i++)																			//@<BlockInfo>jp.vstone.block.for,368,736,912,736,False,34,コメント@</BlockInfo>
		{
																														//@<OutputChild>
			CRobotUtil.Log(getClass().getSimpleName(), (String)"検索対象" + cmdArray[i]);									//@<BlockInfo>jp.vstone.block.printlog,432,736,432,736,False,33,@</BlockInfo>	@<EndOfBlock/>
			{																											//@<BlockInfo>jp.vstone.block.text.regex,496,592,784,592,False,32,textに設定した文字列を正規表現でマッチングする。@</BlockInfo>
				String text = (String)cmdArray[i];
				if(text.matches((String)"cmd"))
				{
				
																														//@<OutputChild>
					isCmd=(boolean)true;																					//@<BlockInfo>jp.vstone.block.calculater,640,592,640,592,False,24,@</BlockInfo>
																															//@<EndOfBlock/>
																																//@</OutputChild>

				}
				else if(text.matches((String)"motion"))
				{
				
																														//@<OutputChild>
					isMotion=(boolean)true;																					//@<BlockInfo>jp.vstone.block.calculater,576,688,576,688,False,27,@</BlockInfo>
																															//@<EndOfBlock/>
					doMotion((String)cmdArray[i+1]);																		//@<BlockInfo>jp.vstone.block.callfunc.base,640,688,640,688,False,26,@</BlockInfo>	@<EndOfBlock/>
					i=(int)i + 1;																							//@<BlockInfo>jp.vstone.block.calculater,704,688,704,688,False,25,@</BlockInfo>
																															//@<EndOfBlock/>
																																//@</OutputChild>

				}
				else if(text.matches((String)"scenario"))
				{
				
																														//@<OutputChild>
					isScenario=true;																						//@<BlockInfo>jp.vstone.block.calculater,576,784,576,784,False,30,@</BlockInfo>
																															//@<EndOfBlock/>
					doScenario((String)cmdArray[i+1]);																		//@<BlockInfo>jp.vstone.block.callfunc.base,640,784,640,784,False,29,@</BlockInfo>	@<EndOfBlock/>
					i=(int)i + 1;																							//@<BlockInfo>jp.vstone.block.calculater,704,784,704,784,False,28,@</BlockInfo>
																															//@<EndOfBlock/>
																																//@</OutputChild>

				}
				else
				{
				
																														//@<OutputChild>
																														//@</OutputChild>

				}
				
			}																											//@<EndOfBlock/>
			if(isCmd==false && i==0){																					//@<BlockInfo>jp.vstone.block.freeproc,848,736,848,736,False,31,@</BlockInfo>
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
	public void doScenario(String strMotionName)																		//@<BlockInfo>jp.vstone.block.func,848,2336,1488,2336,False,50,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.talk.idling.stop,912,2336,1424,2336,False,49,Idling動作を一時的に無効にします@</BlockInfo>
			boolean isIdling = GlobalVariable.sotawish.isPlayIdling();
			if(isIdling) GlobalVariable.sotawish.StopIdling();
			{


																														//@<OutputChild>
				pose = new CRobotPose();																					//@<BlockInfo>jp.vstone.block.pose,976,2336,976,2336,False,48,コメント@</BlockInfo>
				pose.SetPose(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{0,-900,0,900,0,0,0,0}
								);
				pose.SetTorque(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{100,100,100,100,100,100,100,100}
								);
				pose.SetLed(	new Byte[]{0,1,2,8,9,10,11,12,13},
								new Short[]{0,-255,0,180,80,0,180,80,0}
								);
				GlobalVariable.motion.play(pose,500);
				CRobotUtil.wait(500);																						//@<EndOfBlock/>
				switch((String)strMotionName)																				//@<BlockInfo>jp.vstone.block.switch,1056,2048,1344,2048,False,47,@</BlockInfo>
				{
					case (String)"Greeting":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"Greeting ! -- 挨拶します");								//@<BlockInfo>jp.vstone.block.printlog,1152,2048,1152,2048,False,41,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"こんにちは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,1216,2048,1216,2048,False,40,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"Preset1":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"Preset1 ! -- 定型文発話します");							//@<BlockInfo>jp.vstone.block.printlog,1152,2144,1152,2144,False,43,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"僕は、ソータっていうよ。この建物の案内をしているんだ。何か聞きたいことはあるかな？",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,1216,2144,1216,2144,False,42,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"Preset2":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"Preset2 ! -- 定型文発話します");							//@<BlockInfo>jp.vstone.block.printlog,1152,2240,1152,2240,False,45,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"うーん、難しい質問だね。スタッフに聞いてみるから、ちょっと待ってね。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,1216,2240,1216,2240,False,44,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					default:
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"それは登録されてないわー");										//@<BlockInfo>jp.vstone.block.printlog,1184,2336,1184,2336,False,46,@</BlockInfo>	@<EndOfBlock/>
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
	public void auto()																									//@<BlockInfo>jp.vstone.block.func,32,1472,1392,1472,False,73,コメント@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.thread,96,1472,1312,1472,False,72,スレッド@</BlockInfo>
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						
						
																														//@<OutputChild>
						while(net_loop==0)																							//@<BlockInfo>jp.vstone.block.while,160,1472,2160,1728,False,71,TRUE@</BlockInfo>
						{
						
						
																																	//@<OutputChild>
							pose = new CRobotPose();																				//@<BlockInfo>jp.vstone.block.pose,224,1472,224,1472,False,70,コメント@</BlockInfo>
							pose.SetPose(	new Byte[]{1,2,3,4,5,6,7,8},
											new Short[]{0,-900,0,900,0,0,0,0}
											);
							pose.SetTorque(	new Byte[]{1,2,3,4,5,6,7,8},
											new Short[]{100,100,100,100,100,100,100,100}
											);
							pose.SetLed(	new Byte[]{0,1,2,8,9,10,11,12,13},
											new Short[]{0,-255,0,180,80,0,180,80,0}
											);
							GlobalVariable.motion.play(pose,500);
							CRobotUtil.wait(500);																					//@<EndOfBlock/>
							if(isAuto==GlobalVariable.TRUE)																			//@<BlockInfo>jp.vstone.block.if,288,1424,1168,1424,False,69,コメント@</BlockInfo>
							{
																																	//@<OutputChild>
								if(!GlobalVariable.sotawish.isPlayIdling()) GlobalVariable.sotawish.StartIdling();					//@<BlockInfo>jp.vstone.block.talk.idling2,368,1424,1088,1424,False,66,Idling開始@</BlockInfo>
								try{
						
						
																																	//@<OutputChild>
									recogresult = GlobalVariable.recog.getRecognitionwithAbort((int)60000);							//@<BlockInfo>jp.vstone.block.talk.speechrecog.score2,496,992,976,992,False,65,音声認識を行い、認識候補との完全一致で比較する。認識スコアが一番高い結果に分岐する。実際に認識された文字列はspeechRecogResultに代入される@</BlockInfo>
									speechRecogResult = recogresult.CheckBest(new String[]{
									 "こんにちは" ,  "何ができる" ,  "名前は" ,  "ばいばい" ,  "" , 
									},false);
									if(speechRecogResult == null) speechRecogResult = "";
						
									if(speechRecogResult.contains((String)"こんにちは"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"こんにちは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,640,992,640,992,False,51,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"何ができる"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"この建物の案内ができるよ",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,640,1072,640,1072,False,52,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"名前は"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"僕はソータ、っていうよ。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,640,1152,640,1152,False,53,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"ばいばい"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"ばいばい！またきてね！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,640,1232,640,1232,False,54,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											switch(GlobalVariable.random.nextInt((int)16))												//@<BlockInfo>jp.vstone.block.random,576,1520,896,1504,True,64,何か音が聞こえたとき 1/5 (20%) の確率でランダムな定型文を発話する@</BlockInfo>
											{
												case (int)0:
												{
																																		//@<OutputChild>
													dayOrNight();																			//@<BlockInfo>jp.vstone.block.callfunc.base,624,1424,624,1424,False,59,@</BlockInfo>	@<EndOfBlock/>
													if(dayStatus==0)																		//@<BlockInfo>jp.vstone.block.if,688,1328,816,1328,False,58,コメント@</BlockInfo>
													{
																																			//@<OutputChild>
														GlobalVariable.sotawish.Say((String)"おはようございます。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,752,1328,752,1328,False,55,@</BlockInfo>
																																			//@<EndOfBlock/>
																																			//@</OutputChild>
													}
													else if(dayStatus==1)
													{
																																			//@<OutputChild>
														GlobalVariable.sotawish.Say((String)"こんにちは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,752,1424,752,1424,False,56,@</BlockInfo>
																																			//@<EndOfBlock/>
																																			//@</OutputChild>
													}
													else if(dayStatus==2)
													{
																																			//@<OutputChild>
														GlobalVariable.sotawish.Say((String)"こんばんは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,752,1520,752,1520,False,57,@</BlockInfo>
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
													GlobalVariable.sotawish.Say((String)"お仕事、お疲れさまです！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,704,1696,704,1696,False,60,@</BlockInfo>
																																			//@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
												case (int)2:
												{
																																		//@<OutputChild>
													GlobalVariable.sotawish.Say((String)"今、受付をリモートで対応しています！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,704,1776,704,1776,False,61,@</BlockInfo>
																																			//@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
												default:
												{
																																		//@<OutputChild>
													CRobotUtil.Log(getClass().getSimpleName(), (String)"発話を見送るよ。");							//@<BlockInfo>jp.vstone.block.printlog,688,1856,688,1856,False,63,@</BlockInfo>	@<EndOfBlock/>
													GlobalVariable.sotawish.Say((String)"ふうん",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,752,1856,752,1856,False,62,@</BlockInfo>
																																			//@<EndOfBlock/>
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
								if(!GlobalVariable.sotawish.isPlayIdling()) GlobalVariable.sotawish.StartIdling();					//@<BlockInfo>jp.vstone.block.talk.idling2,496,1936,1088,1936,False,115,Idling開始@</BlockInfo>
								try{
						
						
																																	//@<OutputChild>
									CRobotUtil.Log(getClass().getSimpleName(), (String)"auto状態待機");									//@<BlockInfo>jp.vstone.block.printlog,560,1936,560,1936,False,68,@</BlockInfo>	@<EndOfBlock/>
									CRobotUtil.wait((int)100);																		//@<BlockInfo>jp.vstone.block.wait,1024,1936,1024,1936,False,67,コメント@</BlockInfo>	@<EndOfBlock/>
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
	public mymain()																										//@<BlockInfo>jp.vstone.block.func.constructor,48,32,1072,32,False,88,@</BlockInfo>
	{
																														//@<OutputChild>
		net_loop=0;																										//@<BlockInfo>jp.vstone.block.variable,112,32,112,32,False,87,break@</BlockInfo>
																														//@<EndOfBlock/>
		recvMesg="";																									//@<BlockInfo>jp.vstone.block.variable,176,32,176,32,False,86,break@</BlockInfo>
																														//@<EndOfBlock/>
		isAuto=true;																									//@<BlockInfo>jp.vstone.block.variable,240,32,240,32,False,85,break@</BlockInfo>
																														//@<EndOfBlock/>
		behavior=new jp.co.mysota.behavior();																			//@<BlockInfo>jp.vstone.block.variable,304,32,304,32,False,84,break@</BlockInfo>
																														//@<EndOfBlock/>
		isDay=true;																										//@<BlockInfo>jp.vstone.block.variable,368,32,368,32,False,83,break@</BlockInfo>
																														//@<EndOfBlock/>
		dayStatus=0;																									//@<BlockInfo>jp.vstone.block.variable,432,32,432,32,False,82,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*CPlayWave cplay*/;																							//@<BlockInfo>jp.vstone.block.variable,560,32,560,32,False,81,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*String speechRecogResult*/;																					//@<BlockInfo>jp.vstone.block.variable,624,32,624,32,False,80,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*RecogResult recogresult*/;																					//@<BlockInfo>jp.vstone.block.variable,688,32,688,32,False,79,break@</BlockInfo>
																														//@<EndOfBlock/>
		readText="";																									//@<BlockInfo>jp.vstone.block.variable,752,32,752,32,False,78,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*CRobotPose pose*/;																							//@<BlockInfo>jp.vstone.block.variable,816,32,816,32,False,77,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*String time_string*/;																							//@<BlockInfo>jp.vstone.block.variable,880,32,880,32,False,76,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*LocalDateTime localDateTime*/;																				//@<BlockInfo>jp.vstone.block.variable,944,32,944,32,False,75,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*int getDateTimeElement*/;																						//@<BlockInfo>jp.vstone.block.variable,1008,32,1008,32,False,74,break@</BlockInfo>
																														//@<EndOfBlock/>
																														//@</OutputChild>
	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void dayOrNight()																							//@<BlockInfo>jp.vstone.block.func,864,2512,1280,2512,False,94,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		localDateTime = LocalDateTime.now();																			//@<BlockInfo>jp.vstone.block.time.getlocaldatetime,928,2512,928,2512,False,93,ローカル時間をロボット内のカレンダーより取得し、変数LocalDateTime lodalDateTimeに代入。取得した情報から日時などを個別に切り出す場合は「日時の切り出しブロック」を使う@</BlockInfo>
																														//@<EndOfBlock/>
		{																												//@<BlockInfo>jp.vstone.block.time.getdatetimeelement,992,2512,992,2512,False,92,dateTimeに記録された日時情報から、年・月・日・時・分・秒のうち一つをtypeで指定し、変数int getDateTimeElementに代入する@</BlockInfo>
			LocalDateTime d = (LocalDateTime)localDateTime;
			getDateTimeElement = d.getHour();
		}																												//@<EndOfBlock/>
		if(getDateTimeElement>=0 && getDateTimeElement<5){																//@<BlockInfo>jp.vstone.block.freeproc,1072,2512,1072,2512,False,91,@</BlockInfo>
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
		CRobotUtil.Log(getClass().getSimpleName(), (String)"現在の時刻（Hour）は....");											//@<BlockInfo>jp.vstone.block.printlog,1136,2512,1136,2512,False,90,@</BlockInfo>	@<EndOfBlock/>
		CRobotUtil.Log(getClass().getSimpleName(), (String)time_string);												//@<BlockInfo>jp.vstone.block.printlog,1200,2512,1200,2512,False,89,@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void net_main()																								//@<BlockInfo>jp.vstone.block.func,48,432,816,432,False,103,socketでメッセージを受け取る@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.thread,112,432,752,432,False,102,スレッド@</BlockInfo>
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						
						
																														//@<OutputChild>
						{																											//@<BlockInfo>jp.vstone.block.tcpip.server.init,176,432,688,432,False,101,@</BlockInfo>
							TCPIPServer tcpipServer = new TCPIPServer((short)5001,(int)5000);
							
																																	//@<OutputChild>
							while(net_loop==0)																						//@<BlockInfo>jp.vstone.block.while,240,432,624,432,False,100,TRUE@</BlockInfo>
							{
						
						
																																	//@<OutputChild>
								try{																								//@<BlockInfo>jp.vstone.block.tcpip.server,304,384,560,384,False,99,@</BlockInfo>
									GlobalVariable.recvString = tcpipServer.waitRequest();
						
									if(GlobalVariable.recvString==null) GlobalVariable.recvString="";
									if(GlobalVariable.recvString.contentEquals((String)"cmd;end"))
									{
																																	//@<OutputChild>
										net_loop=(short)1;																				//@<BlockInfo>jp.vstone.block.calculater,368,384,368,384,False,96,@</BlockInfo>
																																		//@<EndOfBlock/>
										CRobotUtil.wait((int)2000);																		//@<BlockInfo>jp.vstone.block.wait,464,384,464,384,False,95,コメント@</BlockInfo>	@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else
									{
																																	//@<OutputChild>
										recvMesg=(String)GlobalVariable.recvString;														//@<BlockInfo>jp.vstone.block.calculater,368,480,368,480,False,98,@</BlockInfo>
																																		//@<EndOfBlock/>
										CRobotUtil.Log(getClass().getSimpleName(), (String)recvMesg);									//@<BlockInfo>jp.vstone.block.printlog,432,480,432,480,False,97,@</BlockInfo>	@<EndOfBlock/>
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
	public void main()																									//@<BlockInfo>jp.vstone.block.func,48,208,944,208,False,114,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		GlobalVariable.sotawish.Say((String)"こんにちは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,112,208,112,208,False,113,@</BlockInfo>
																														//@<EndOfBlock/>
		net_main();																										//@<BlockInfo>jp.vstone.block.callfunc.base,192,208,192,208,False,112,@</BlockInfo>	@<EndOfBlock/>
		auto();																											//@<BlockInfo>jp.vstone.block.callfunc.base,256,208,256,208,False,111,@</BlockInfo>	@<EndOfBlock/>
		if(!GlobalVariable.sotawish.isPlayIdling()) GlobalVariable.sotawish.StartIdling();								//@<BlockInfo>jp.vstone.block.talk.idling2,336,208,880,208,False,110,Idling開始@</BlockInfo>
		try{


																														//@<OutputChild>
			while(net_loop==0)																							//@<BlockInfo>jp.vstone.block.while,400,208,816,208,False,109,TRUE@</BlockInfo>
			{


																														//@<OutputChild>
				if(recvMesg!="")																						//@<BlockInfo>jp.vstone.block.if,464,160,752,160,False,108,コメント@</BlockInfo>
				{
																														//@<OutputChild>
					String argString=recvMesg;																			//@<BlockInfo>jp.vstone.block.variable,528,160,528,160,False,106,break@</BlockInfo>
																														//@<EndOfBlock/>
					recvMesg=(String)"";																				//@<BlockInfo>jp.vstone.block.calculater,608,160,608,160,False,105,@</BlockInfo>
																														//@<EndOfBlock/>
					teleop((String)argString);																			//@<BlockInfo>jp.vstone.block.callfunc.base,688,160,688,160,False,104,@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>
				}
				else
				{
																														//@<OutputChild>
					CRobotUtil.wait((int)100);																			//@<BlockInfo>jp.vstone.block.wait,608,256,608,256,False,107,コメント@</BlockInfo>	@<EndOfBlock/>
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

}
