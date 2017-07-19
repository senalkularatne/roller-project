/*
 * Copyright 2010-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Code from Spring Mobile and modified for use in Apache Roller
 * https://github.com/spring-projects/spring-mobile 11 Feb 2014
 * 
 */
package org.apache.roller.weblogger.ui.rendering.util.mobile;

/**
 * Enumeration for the type of device that has been resolved
 * 
 * @author Roy Clarkson
 */
public enum DeviceType {

	/**
	 * Represents a normal device. i.e. a browser on a desktop or laptop
	 * computer. DO NOT USE FOR COMPARISON.
	 */
	NORMAL,

	/**
	 * Represents a mobile device, such as an iPhone. DO NOT USE FOR COMPARISON.
	 */
	MOBILE,

	/**
	 * Represents a tablet device, such as an iPad. DO NOT USE FOR COMPARISON.
	 */
	TABLET
}
