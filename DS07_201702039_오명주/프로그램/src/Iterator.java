
public interface Iterator<T> {
	public boolean hasNext(); // 다음 원소가 존재하는지 여부 반환
	public T next(); // 다음 원소를 얻어냄. 없으면 null반환
}
