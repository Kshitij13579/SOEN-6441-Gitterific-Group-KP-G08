package modules;

import com.google.inject.AbstractModule;

import model.GithubApi;
import model.GithubApiImpl;

/**
 * Class containing configuration for binding Github implementation class to the GithubAPI interface
 * @author Mrinal Rai
 * @since 2021-11-20
 * @version 1.0
 *
 */
public class GithubModule extends AbstractModule {
	/**
	 * Binds the Interface implementation to the Interface
	 * @author Mrinal Rai
	 * @since 2021-11-20
	 */
	@Override
	protected final void configure() {
		bind(GithubApi.class).to(GithubApiImpl.class);
	}
}
