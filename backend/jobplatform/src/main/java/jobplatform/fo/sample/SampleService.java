package jobplatform.fo.sample;

public interface SampleService {

    public Long insert(SampleEntity sampleEntity);

    public void delete(Long id);

    public void update(SampleEntity sampleEntity);

    public SampleEntity gett(Long id);
}
