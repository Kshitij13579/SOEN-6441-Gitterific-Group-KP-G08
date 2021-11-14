package modules;

import com.google.inject.AbstractModule;

import model.GithubApi;
import model.GithubApiImpl;

public class GithubModule extends AbstractModule {
	@Override
	protected final void configure() {
		bind(GithubApi.class).to(GithubApiImpl.class);
	}
}
