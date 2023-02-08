package com.tdd.backend.render;

import java.util.List;

public enum SpecificCarOption {
	IONIC6(List.of(
		RenderingOption.FRONT_PDW, RenderingOption.BACK_PDW,
		RenderingOption.BCA, RenderingOption.SEA, RenderingOption.LKA,
		RenderingOption.LED_HEADLAMP, RenderingOption.SUN_ROOF,
		RenderingOption.VENTILATION_SEAT, RenderingOption.IMS, RenderingOption.HEATED_SEAT,
		RenderingOption.REAR_MONITOR, RenderingOption.BLIND_SPOT_VIEW_MONITOR, RenderingOption.SURROUND_VIEW_MONITOR,
		RenderingOption.NAVIGATION, RenderingOption.PREMIUM_SOUND_SYSTEM
	)),

	IONIC5(List.of(
		RenderingOption.FRONT_PDW, RenderingOption.BACK_PDW,
		RenderingOption.BCA, RenderingOption.SEA, RenderingOption.LKA,
		RenderingOption.LED_HEADLAMP, RenderingOption.SUN_ROOF,
		RenderingOption.VENTILATION_SEAT, RenderingOption.IMS, RenderingOption.HEATED_SEAT,
		RenderingOption.REAR_MONITOR, RenderingOption.BLIND_SPOT_VIEW_MONITOR, RenderingOption.SURROUND_VIEW_MONITOR,
		RenderingOption.NAVIGATION, RenderingOption.PREMIUM_SOUND_SYSTEM
	)),

	NEXO(List.of(
		RenderingOption.FRONT_PDW, RenderingOption.BACK_PDW,
		RenderingOption.BCA, RenderingOption.LKA,
		RenderingOption.LED_HEADLAMP, RenderingOption.ROOF_RACK, RenderingOption.SUN_ROOF,
		RenderingOption.VENTILATION_SEAT, RenderingOption.HEATED_SEAT,
		RenderingOption.REAR_MONITOR, RenderingOption.BLIND_SPOT_VIEW_MONITOR, RenderingOption.SURROUND_VIEW_MONITOR,
		RenderingOption.NAVIGATION, RenderingOption.PREMIUM_SOUND_SYSTEM
	)),

	AVANTE(List.of(
		RenderingOption.VIBRATION_WARNING_STEERING_WHEEL,
		RenderingOption.BCA, RenderingOption.LKA,
		RenderingOption.LED_HEADLAMP, RenderingOption.SUN_ROOF,
		RenderingOption.VENTILATION_SEAT, RenderingOption.IMS, RenderingOption.HEATED_SEAT,
		RenderingOption.REAR_MONITOR,
		RenderingOption.DISPLAY_AUDIO, RenderingOption.NAVIGATION, RenderingOption.PREMIUM_SOUND_SYSTEM
	)),

	SONATA(List.of(
		RenderingOption.FRONT_PDW, RenderingOption.BACK_PDW, RenderingOption.VIBRATION_WARNING_STEERING_WHEEL,
		RenderingOption.BCA, RenderingOption.SEA, RenderingOption.LKA,
		RenderingOption.VENTILATION_SEAT, RenderingOption.IMS, RenderingOption.HEATED_SEAT,
		RenderingOption.REAR_MONITOR, RenderingOption.BLIND_SPOT_VIEW_MONITOR, RenderingOption.SURROUND_VIEW_MONITOR,
		RenderingOption.DISPLAY_AUDIO, RenderingOption.NAVIGATION, RenderingOption.PREMIUM_SOUND_SYSTEM
	)),

	THE_ALL_NEW_GRANDEUR(List.of(
		RenderingOption.FRONT_PDW, RenderingOption.BACK_PDW,
		RenderingOption.BCA, RenderingOption.SEA, RenderingOption.LKA,
		RenderingOption.VENTILATION_SEAT, RenderingOption.IMS, RenderingOption.HEATED_SEAT,
		RenderingOption.REAR_MONITOR, RenderingOption.BLIND_SPOT_VIEW_MONITOR, RenderingOption.SURROUND_VIEW_MONITOR,
		RenderingOption.NAVIGATION
	)),

	SANTAFE(List.of(
		RenderingOption.FRONT_PDW, RenderingOption.BACK_PDW,
		RenderingOption.BCA, RenderingOption.SEA, RenderingOption.LKA,
		RenderingOption.LED_HEADLAMP, RenderingOption.ROOF_RACK,
		RenderingOption.VENTILATION_SEAT, RenderingOption.IMS, RenderingOption.HEATED_SEAT,
		RenderingOption.REAR_MONITOR, RenderingOption.BLIND_SPOT_VIEW_MONITOR, RenderingOption.SURROUND_VIEW_MONITOR,
		RenderingOption.NAVIGATION, RenderingOption.PREMIUM_SOUND_SYSTEM
	)),

	PALASADE(List.of(
		RenderingOption.FRONT_PDW, RenderingOption.BACK_PDW, RenderingOption.VIBRATION_WARNING_STEERING_WHEEL,
		RenderingOption.BCA, RenderingOption.SEA, RenderingOption.LKA,
		RenderingOption.ROOF_RACK, RenderingOption.SUN_ROOF,
		RenderingOption.VENTILATION_SEAT, RenderingOption.IMS,
		RenderingOption.REAR_MONITOR, RenderingOption.BLIND_SPOT_VIEW_MONITOR, RenderingOption.SURROUND_VIEW_MONITOR,
		RenderingOption.NAVIGATION, RenderingOption.PREMIUM_SOUND_SYSTEM
	)),

	TUSCON(List.of(
		RenderingOption.VIBRATION_WARNING_STEERING_WHEEL,
		RenderingOption.BCA, RenderingOption.SEA,
		RenderingOption.ROOF_RACK,
		RenderingOption.VENTILATION_SEAT, RenderingOption.IMS, RenderingOption.HEATED_SEAT,
		RenderingOption.REAR_MONITOR, RenderingOption.BLIND_SPOT_VIEW_MONITOR, RenderingOption.SURROUND_VIEW_MONITOR,
		RenderingOption.NAVIGATION, RenderingOption.PREMIUM_SOUND_SYSTEM, RenderingOption.PREMIUM_SOUND_SYSTEM
	)),

	THE_ALL_NEW_KONA(List.of(
		RenderingOption.FRONT_PDW, RenderingOption.BACK_PDW,
		RenderingOption.BCA, RenderingOption.LKA,
		RenderingOption.LED_HEADLAMP, RenderingOption.ROOF_RACK, RenderingOption.SUN_ROOF,
		RenderingOption.VENTILATION_SEAT, RenderingOption.IMS, RenderingOption.HEATED_SEAT,
		RenderingOption.REAR_MONITOR, RenderingOption.BLIND_SPOT_VIEW_MONITOR, RenderingOption.SURROUND_VIEW_MONITOR
	));

	private final List<RenderingOption> optionList;

	SpecificCarOption(List<RenderingOption> options) {
		this.optionList = options;
	}

	public static SpecificCarOption getCar(String name) {
		return valueOf(name.toUpperCase());
	}

	public List<RenderingOption> getOptions() {
		return optionList;
	}

}
