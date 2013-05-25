public enum TestEnum {
	A, B;

	public String getName() {
		if (this == A) {
			return "Huch";
		}
		return "Haha";
	}
}
