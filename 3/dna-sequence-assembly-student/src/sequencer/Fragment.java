package sequencer;

public class Fragment {
	private String nucleotides;

	public Fragment(String nucleotides) throws IllegalArgumentException {
		for (char c : nucleotides.toCharArray()) {
			if (c != 'A' && c != 'C' && c != 'G' && c != 'T') {
				throw new IllegalArgumentException("Invalid character in nucleotide sequence: " + c);
			}
		}
		this.nucleotides = nucleotides;
	}

	public int length() {
		return nucleotides.length();
	}

	@Override
	public String toString() {
		return nucleotides;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Fragment)) {
			return false;
		}
		Fragment other = (Fragment) o;
		return this.nucleotides.equals(other.nucleotides);
	}

	public int calculateOverlap(Fragment f) {
		int maxLength = Math.min(this.length(), f.length());
		for (int i = maxLength; i > 0; i--) {
			if (this.nucleotides.endsWith(f.nucleotides.substring(0, i))) {
				return i;
			}
		}
		return 0;
	}

	public Fragment mergedWith(Fragment f) {
		int overlap = calculateOverlap(f);
		String mergedSequence = this.nucleotides + f.nucleotides.substring(overlap);
		return new Fragment(mergedSequence);
	}
}
