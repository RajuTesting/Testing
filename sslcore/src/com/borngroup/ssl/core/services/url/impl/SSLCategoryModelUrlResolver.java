/**
 *
 */
package com.borngroup.ssl.core.services.url.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.url.impl.DefaultCategoryModelUrlResolver;

import java.util.List;


/**
 * @author kanagaraj.k
 *
 */
public class SSLCategoryModelUrlResolver extends DefaultCategoryModelUrlResolver
{

    @Override
    protected String buildPathString(final List<CategoryModel> path)
    {
        final StringBuilder result = new StringBuilder();
        for (int i = 1; i < path.size(); i++)
        {
            if (i != 1)
            {
                result.append('-');
            }
            result.append(urlSafe(path.get(i).getName()));
        }
        String urlStr = result.toString();
        if (urlStr.endsWith("-"))
        {
            urlStr = urlStr.substring(0, urlStr.length() - 1);
        }
        return urlStr;
    }

    @Override
    protected String urlSafe(final String text)
    {
        if ((text == null) || (text.isEmpty()))
        {
            return "";
        }
        String cleanedText = text.toLowerCase().replaceAll("[^\\w]", "-");
        cleanedText = cleanedText.replaceAll("-+", "-");
        return cleanedText;
    }
}

