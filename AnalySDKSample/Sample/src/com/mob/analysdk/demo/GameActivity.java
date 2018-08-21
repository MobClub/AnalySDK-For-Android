package com.mob.analysdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.mob.analysdk.AnalySDK;
import com.mob.game.GameUserEvent;
import com.mob.game.PayEvent;
import com.mob.game.RoleEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * Created by yjin on 2018/1/29.
 */

public class GameActivity extends Activity implements View.OnClickListener {

	private boolean isOnlyEvent;
	private ArrayList<String> onlyEventTags;
	private Random random;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		onlyEventTags = new ArrayList<String>();
		random = new Random();
		init();
	}

	private void init(){
		findViewById(R.id.payEvent).setOnClickListener(this);
		findViewById(R.id.userCreate).setOnClickListener(this);
		findViewById(R.id.userLogin).setOnClickListener(this);
		findViewById(R.id.userUpdate).setOnClickListener(this);
		findViewById(R.id.roleCreate).setOnClickListener(this);
		findViewById(R.id.roleLogin).setOnClickListener(this);
		findViewById(R.id.roleUpdate).setOnClickListener(this);
		findViewById(R.id.setEventOnly).setOnClickListener(this);
		findViewById(R.id.setEventStack).setOnClickListener(this);
		findViewById(R.id.behaviorStart).setOnClickListener(this);
		findViewById(R.id.behaviorEnd).setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("params", "openGamePage");
		AnalySDK.behaviorStart("GamePage", map);
	}

	@Override
	protected void onPause() {
		super.onPause();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("params", "closeGamePage");
		AnalySDK.behaviorEnd("GamePage", map);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.payEvent:
				trackPayEvent();
				break;
			case R.id.userCreate:
				userRegist();
				break;
			case R.id.userLogin:
				userLogin();
				break;
			case R.id.userUpdate:
				userUpdate();
				break;
			case R.id.roleCreate:
				roleCreate();
				break;
			case R.id.roleLogin:
				roleLogin();
				break;
			case R.id.roleUpdate:
				roleUpdate();
				break;
			case R.id.behaviorStart:
				if (isOnlyEvent) {
					Object object = new Object();
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("onlyEventTag", String.valueOf(object.hashCode()));
					onlyEventTags.add(String.valueOf(object.hashCode()));
					AnalySDK.behaviorStart("gameEvent", String.valueOf(object.hashCode()), map);
				} else {
					AnalySDK.behaviorStart("stackEvent", null);
				}
				break;
			case R.id.behaviorEnd:
				if (isOnlyEvent) {
					String onlyEventTag = "test";
					if (onlyEventTags.size() > 0) {
						onlyEventTag = onlyEventTags.get(random.nextInt(onlyEventTags.size()));
					}
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("onlyEventTag", onlyEventTag);
					onlyEventTags.remove(onlyEventTag);
					AnalySDK.behaviorEnd("gameEvent", onlyEventTag, map);
				} else {
					AnalySDK.behaviorEnd("stackEvent", null);
				}
				break;
			case R.id.setEventStack:
				isOnlyEvent = false;
				break;
			case R.id.setEventOnly:
				isOnlyEvent = true;
				break;
		}

	}

	private static PayEvent createPayEvent(){
		PayEvent payEvent = new PayEvent("12313123","hehheh");
		payEvent.payMoney = 33;
		payEvent.payContent = "月卡";
		payEvent.payType = "微信";
		payEvent.payActivity = "圣诞活动";
		payEvent.payDiscount = 12;
		payEvent.discountReason = "新用户打折";
		return payEvent;
	}

	private static GameUserEvent createUserEvent(){
		GameUserEvent gameUserEvent = new GameUserEvent("1231312","小伙子");
		gameUserEvent.regType = "微信";
		gameUserEvent.regChannel ="应用宝";
		gameUserEvent.userType = "测试账号";
		gameUserEvent.addication = "是";
		gameUserEvent.money = 12;
		gameUserEvent.gender = "男";
		gameUserEvent.county = "中国";
		gameUserEvent.province = "上海";
		gameUserEvent.city = "上海";
		gameUserEvent.age = 19;
		gameUserEvent.constellation ="处女";
		gameUserEvent.zodiac = "long";
		gameUserEvent.nickname = "西装情兽";
		return gameUserEvent;
	}

	private static RoleEvent createRoleEvent(){
		RoleEvent roleEvent = new RoleEvent("23123131","小米子");
		roleEvent.roServer ="绝命一区";
		roleEvent.roName = "剥皮人";
		roleEvent.roCareer = "九魂道人";
		roleEvent.roLevel = 999+1;
		roleEvent.roVip = "vip4";
		roleEvent.roRankLevel = "青铜";
		roleEvent.roEnergy = 32;
		roleEvent.roMoney = 55;
		roleEvent.roCoin = 33;
		roleEvent.roSource1 = 11;
		roleEvent.roSource2 = 22;
		roleEvent.roSource3 =33;
		roleEvent.roSource4 = 44;
		return roleEvent;
	}

	/**
	 *  支付事件统计
	 */
	public static synchronized void trackPayEvent(){
		AnalySDK.trackPayEvent(createPayEvent());
	}

	/**
	 * 用户注册
	 */
	public static synchronized void userRegist(){
		AnalySDK.userRegist(createUserEvent());
	}

	/**
	 * 用户登记
	 */
	public static synchronized void userLogin(){
		AnalySDK.userLogin(createUserEvent());
	}

	/**
	 *  用户信息更新
	 */
	public static synchronized void userUpdate(){
		AnalySDK.userUpdate(createUserEvent());
	}

	/**
	 *  角色创建
	 */
	public static synchronized void roleCreate(){
		AnalySDK.roleCreate(createRoleEvent());
	}

	/**
	 * 角色登录
	 */
	public static synchronized void roleLogin(){
		AnalySDK.roleLogin(createRoleEvent());
	}

	/**
	 * 角色信息更新
	 */
	public static synchronized void roleUpdate(){
		AnalySDK.roleUpdate(createRoleEvent());
	}
}
