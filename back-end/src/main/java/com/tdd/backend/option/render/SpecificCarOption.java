package com.tdd.backend.option.render;

import java.util.List;

import com.tdd.backend.option.EntireOption;

public enum SpecificCarOption {
	IONIC6(List.of(
		EntireOption.FRONT_PDW, EntireOption.BACK_PDW,
		EntireOption.BCA, EntireOption.SEA, EntireOption.LKA,
		EntireOption.LED_HEADLAMP, EntireOption.SUN_ROOF,
		EntireOption.VENTILATION_SEAT, EntireOption.IMS, EntireOption.HEATED_SEAT,
		EntireOption.REAR_MONITOR, EntireOption.BLIND_SPOT_VIEW_MONITOR, EntireOption.SURROUND_VIEW_MONITOR,
		EntireOption.NAVIGATION, EntireOption.PREMIUM_SOUND_SYSTEM
	)),

	IONIC5(List.of(
		EntireOption.FRONT_PDW, EntireOption.BACK_PDW,
		EntireOption.BCA, EntireOption.SEA, EntireOption.LKA,
		EntireOption.LED_HEADLAMP, EntireOption.SUN_ROOF,
		EntireOption.VENTILATION_SEAT, EntireOption.IMS, EntireOption.HEATED_SEAT,
		EntireOption.REAR_MONITOR, EntireOption.BLIND_SPOT_VIEW_MONITOR, EntireOption.SURROUND_VIEW_MONITOR,
		EntireOption.NAVIGATION, EntireOption.PREMIUM_SOUND_SYSTEM
	)),

	NEXO(List.of(
		EntireOption.FRONT_PDW, EntireOption.BACK_PDW,
		EntireOption.BCA, EntireOption.LKA,
		EntireOption.LED_HEADLAMP, EntireOption.ROOF_RACK, EntireOption.SUN_ROOF,
		EntireOption.VENTILATION_SEAT, EntireOption.HEATED_SEAT,
		EntireOption.REAR_MONITOR, EntireOption.BLIND_SPOT_VIEW_MONITOR, EntireOption.SURROUND_VIEW_MONITOR,
		EntireOption.NAVIGATION, EntireOption.PREMIUM_SOUND_SYSTEM
	)),

	AVANTE(List.of(
		EntireOption.VIBRATION_WARNING_STEERING_WHEEL,
		EntireOption.BCA, EntireOption.LKA,
		EntireOption.LED_HEADLAMP, EntireOption.SUN_ROOF,
		EntireOption.VENTILATION_SEAT, EntireOption.IMS, EntireOption.HEATED_SEAT,
		EntireOption.REAR_MONITOR,
		EntireOption.DISPLAY_AUDIO, EntireOption.NAVIGATION, EntireOption.PREMIUM_SOUND_SYSTEM
	)),

	SONATA(List.of(
		EntireOption.FRONT_PDW, EntireOption.BACK_PDW, EntireOption.VIBRATION_WARNING_STEERING_WHEEL,
		EntireOption.BCA, EntireOption.SEA, EntireOption.LKA,
		EntireOption.VENTILATION_SEAT, EntireOption.IMS, EntireOption.HEATED_SEAT,
		EntireOption.REAR_MONITOR, EntireOption.BLIND_SPOT_VIEW_MONITOR, EntireOption.SURROUND_VIEW_MONITOR,
		EntireOption.DISPLAY_AUDIO, EntireOption.NAVIGATION, EntireOption.PREMIUM_SOUND_SYSTEM
	)),

	THE_ALL_NEW_GRANDEUR(List.of(
		EntireOption.FRONT_PDW, EntireOption.BACK_PDW,
		EntireOption.BCA, EntireOption.SEA, EntireOption.LKA,
		EntireOption.VENTILATION_SEAT, EntireOption.IMS, EntireOption.HEATED_SEAT,
		EntireOption.REAR_MONITOR, EntireOption.BLIND_SPOT_VIEW_MONITOR, EntireOption.SURROUND_VIEW_MONITOR,
		EntireOption.NAVIGATION
	)),

	SANTAFE(List.of(
		EntireOption.FRONT_PDW, EntireOption.BACK_PDW,
		EntireOption.BCA, EntireOption.SEA, EntireOption.LKA,
		EntireOption.LED_HEADLAMP, EntireOption.ROOF_RACK,
		EntireOption.VENTILATION_SEAT, EntireOption.IMS, EntireOption.HEATED_SEAT,
		EntireOption.REAR_MONITOR, EntireOption.BLIND_SPOT_VIEW_MONITOR, EntireOption.SURROUND_VIEW_MONITOR,
		EntireOption.NAVIGATION, EntireOption.PREMIUM_SOUND_SYSTEM
	)),

	PALASADE(List.of(
		EntireOption.FRONT_PDW, EntireOption.BACK_PDW, EntireOption.VIBRATION_WARNING_STEERING_WHEEL,
		EntireOption.BCA, EntireOption.SEA, EntireOption.LKA,
		EntireOption.ROOF_RACK, EntireOption.SUN_ROOF,
		EntireOption.VENTILATION_SEAT, EntireOption.IMS,
		EntireOption.REAR_MONITOR, EntireOption.BLIND_SPOT_VIEW_MONITOR, EntireOption.SURROUND_VIEW_MONITOR,
		EntireOption.NAVIGATION, EntireOption.PREMIUM_SOUND_SYSTEM
	)),

	TUSCON(List.of(
		EntireOption.VIBRATION_WARNING_STEERING_WHEEL,
		EntireOption.BCA, EntireOption.SEA,
		EntireOption.ROOF_RACK,
		EntireOption.VENTILATION_SEAT, EntireOption.IMS, EntireOption.HEATED_SEAT,
		EntireOption.REAR_MONITOR, EntireOption.BLIND_SPOT_VIEW_MONITOR, EntireOption.SURROUND_VIEW_MONITOR,
		EntireOption.NAVIGATION, EntireOption.PREMIUM_SOUND_SYSTEM, EntireOption.PREMIUM_SOUND_SYSTEM
	)),

	THE_ALL_NEW_KONA(List.of(
		EntireOption.FRONT_PDW, EntireOption.BACK_PDW,
		EntireOption.BCA, EntireOption.LKA,
		EntireOption.LED_HEADLAMP, EntireOption.ROOF_RACK, EntireOption.SUN_ROOF,
		EntireOption.VENTILATION_SEAT, EntireOption.IMS, EntireOption.HEATED_SEAT,
		EntireOption.REAR_MONITOR, EntireOption.BLIND_SPOT_VIEW_MONITOR, EntireOption.SURROUND_VIEW_MONITOR
	));

	private final List<EntireOption> optionList;

	SpecificCarOption(List<EntireOption> options) {
		this.optionList = options;
	}

	public static SpecificCarOption getCar(String name) {
		return valueOf(name.toUpperCase());
	}

	public List<EntireOption> getOptions() {
		return optionList;
	}

}
