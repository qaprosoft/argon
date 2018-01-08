package com.qaprosoft.argon.dbaccess.dao.mysql.search;

import java.util.List;

public class SearchResult<T> extends SearchCriteria
{
	private List<T> results;
	private Integer totalResults;

	public SearchResult()
	{
	}

	public SearchResult(SearchCriteria sc)
	{
		this.setPage(sc.getPage());
		this.setPageSize(sc.getPageSize());
		this.setSortOrder(sc.getSortOrder());
	}

	public List<T> getResults()
	{
		return results;
	}

	public void setResults(List<T> results)
	{
		this.results = results;
	}

	public Integer getTotalResults()
	{
		return totalResults;
	}

	public void setTotalResults(Integer totalResults)
	{
		this.totalResults = totalResults;
	}
}
