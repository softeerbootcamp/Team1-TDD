package com.tdd.backend.render;

import static com.tdd.backend.post.Category.*;

import com.tdd.backend.post.Category;

import lombok.Getter;

@Getter
public enum EntireOption {

	FRONT_PDW(SAFETY_OR_PERFORMANCE, "주차거리경고-전방"),
	BACK_PDW(SAFETY_OR_PERFORMANCE, "주차거리경고-후방"),
	VIBRATION_WARNING_STEERING_WHEEL(SAFETY_OR_PERFORMANCE, "진동경고 스티어링 휠"),

	BCA(INTELLIGENT_TECHNOLOGY, "후측방 충돌방지 보조"),
	SEA(INTELLIGENT_TECHNOLOGY, "안전 하차 보조"),
	LKA(INTELLIGENT_TECHNOLOGY, "차량 이탈방지 보조"),

	LED_HEADLAMP(INTERIOR_OR_EXTERIOR, "LED 헤드램프"),
	ROOF_RACK(INTERIOR_OR_EXTERIOR, "루프 랙"),
	SUN_ROOF(INTERIOR_OR_EXTERIOR, "선루프"),

	VENTILATION_SEAT(SEAT, "동승석 통풍시트"),
	IMS(SEAT, "운전석 자세 메모리 시스템"),
	HEATED_SEAT(SEAT, "뒷좌석 열선시트"),

	REAR_MONITOR(CONVENIENCE, "후방 모니터"),
	BLIND_SPOT_VIEW_MONITOR(CONVENIENCE, "후측방 모니터"),
	SURROUND_VIEW_MONITOR(CONVENIENCE, "서라운드 뷰 모니터"),

	DISPLAY_AUDIO(MULTIMEDIA, "디스플레이 오디오"),
	NAVIGATION(MULTIMEDIA, "내비게이션"),
	PREMIUM_SOUND_SYSTEM(MULTIMEDIA, "프리미엄 사운드 시스템");

	private final Category category;
	private final String name;

	EntireOption(Category category, String name) {
		this.category = category;
		this.name = name;
	}

}
