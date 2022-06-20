package com.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Major {

	PHYSICS("Physics"), CHEMISTRY("Chemistry"), MATHS("Maths"), BIOLOGY("Biology"), @JsonEnumDefaultValue
	UNKNOWN_ENUM("UnknownEnum");

	private String value;
	private static final Map<String, Major> mapOfMajors;

	Major(String value) {
		this.value = value;
	}
	
	static {
		mapOfMajors = Collections.unmodifiableMap(
				Arrays.asList(Major.values()).stream().collect(Collectors.toMap(Major::value, Function.identity())));
	}

	@JsonValue
	public String value() {
		return value;
	}

	public static Major lookupValue(String value) {
		return mapOfMajors.get(value);
	}

}
