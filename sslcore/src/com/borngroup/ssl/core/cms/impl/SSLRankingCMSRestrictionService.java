package com.borngroup.ssl.core.cms.impl;

import de.hybris.platform.acceleratorcms.services.impl.RankingCMSRestrictionService;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.restrictions.AbstractRestrictionModel;
import de.hybris.platform.cms2.servicelayer.data.RestrictionData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;


/**
 * SSL specific implementation for {@link RankingCMSRestrictionService}
 * 
 * @author Monomoy.Ghosh
 */
public class SSLRankingCMSRestrictionService extends RankingCMSRestrictionService
{

	private static final Logger LOG = Logger.getLogger(SSLRankingCMSRestrictionService.class);

	/**
	 * @see de.hybris.platform.acceleratorcms.services.impl.RankingCMSRestrictionService#evaluatePages(java.util.Collection,
	 *      de.hybris.platform.cms2.servicelayer.data.RestrictionData)
	 */
	@Override
	public Collection<AbstractPageModel> evaluatePages(final Collection<AbstractPageModel> pages, final RestrictionData data)
	{
		final NavigableMap<Integer, List<AbstractPageModel>> allowedPages = new TreeMap<>();

		final Collection<AbstractPageModel> defaultPages = getDefaultPages(pages);
		for (final AbstractPageModel page : pages)
		{
			if (defaultPages.contains(page))
			{
				continue;
			}

			final List<AbstractRestrictionModel> restrictions = page.getRestrictions();
			if (restrictions == null || restrictions.isEmpty())
			{
				LOG.debug("Page [" + page.getName() + "] is not default page and contains no restrictions. Skipping this page.");
			}
			else
			{
				LOG.debug("Evaluating restrictions for page [" + page.getName() + "].");
				final boolean onlyOneRestrictionMustApply = page.isOnlyOneRestrictionMustApply();
				final boolean allowed = evaluate(restrictions, data, onlyOneRestrictionMustApply);
				if (allowed)
				{
					LOG.debug("Adding page [" + page.getName() + "] to allowed pages");
					final Integer countOfMatchingRestrictions = Integer.valueOf(onlyOneRestrictionMustApply ? 1 : restrictions.size());

					if (allowedPages.containsKey(countOfMatchingRestrictions))
					{
						// Add to existing list
						allowedPages.get(countOfMatchingRestrictions).add(page);
					}
					else
					{
						// Add a new entry
						final List<AbstractPageModel> list = new ArrayList<AbstractPageModel>();
						list.add(page);
						allowedPages.put(countOfMatchingRestrictions, list);
					}
				}
			}
		}

		final List<AbstractPageModel> result = new ArrayList<AbstractPageModel>();

		if (MapUtils.isNotEmpty(allowedPages))
		{
			// Take the highest match count
			result.addAll(allowedPages.lastEntry().getValue());
		}
		else
		{
			if (defaultPages.size() > 1)
			{
				LOG.warn(createMoreThanOneDefaultPageWarning(defaultPages));
			}
			if (CollectionUtils.isNotEmpty(defaultPages))
			{
				LOG.debug("Returning default page");
				result.addAll(defaultPages);
			}
		}

		return result;
	}

}