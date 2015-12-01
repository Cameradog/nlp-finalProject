package selenium.actions;

/**
 * ブラウザ判別用
 *
 */
enum Browser
{
	FIREFOX,
	IE,
	CHROME,
	OPERA
}

/**
 * HTML要素判別用
 *
 */
public enum Element_Type
{
	ID,
	NAME,
	LINK,
	CSS,
	XPATH,
	CLASS,
	TAG
}

/**
 * 例外タイプ判別用
 *
 */
enum Exception_Type
{
	STEALE,
	OK,
	FALSE,
	INVALID,
	NOTVISIBUL,
}