package org.jelly.code;
/**
 * 系统参数类型枚举
 * @author Lychie Fan
 * @since 1.0.0
 */
public enum SystemCode implements Code {
	
	/**
	 * os.name
	 */
	OS_NAME("os.name"), 

	/**
	 * user.name
	 */
	USER_NAME("user.name"), 

	/**
	 * user.home
	 */
	USER_HOME("user.home"), 

	/**
	 * user.dir
	 */
	USER_DIR("user.dir"), 

	/**
	 * java.version
	 */
	JAVA_VERSION("java.version"), 

	/**
	 * java.vm.name
	 */
	JAVA_VM_NAME("java.vm.name"), 

	/**
	 * java.io.tmpdir
	 */
	JAVA_IO_TMPDIR("java.io.tmpdir"), 

	/**
	 * java.class.path
	 */
	CLASS_PATH_DIR("java.class.path"), 

	/**
	 * file.encoding
	 */
	FILE_ENCODING("file.encoding");

	private String code;
	
	private SystemCode(String code){
		this.code = code;
	}
	
	@Override
	public String toFace() {
		return this.name();
	}

	@Override
	public String toCode() {
		return code;
	}
	
}