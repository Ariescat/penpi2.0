package org.penpi.test.commons;

import java.util.UUID;

import org.junit.Test;

public class UUIDTest {
	
	@Test
	public void testGenerate() {
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		System.out.println(uuid.toString().getBytes().length);
	}

}