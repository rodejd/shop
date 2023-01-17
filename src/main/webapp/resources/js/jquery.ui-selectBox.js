/*
	
	jQuery selectBox (version 0.1)
	
		A cosmetic, styleable replacement for SELECT elements
		
		Homepage:   http://abeautifulsite.net/blog/2011/01/jquery-selectbox-plugin/
		Demo page:  http://labs.abeautifulsite.net/projects/js/jquery/selectBox/
		
		Copyright 2011 A Beautiful Site, LLC.
	
	
	License:
		
		Licensed under both the MIT license and the GNU GPL (same as jQuery)
	
	
	Usage:
		
		Link to the JS file:
			
			<script src="jquery.ui-selectbox.min.js" type="text/javascript"></script>
		
		Add the CSS file (or append contents of your own):
		
			<link href="jquery.ui-selectbox.css" rel="stylesheet" type="text/css" />
		
		To create:
			
			$("SELECT").selectBox();
		
		To destroy:
		
			$("SELECT").selectBox('destroy');
		
		Note: you can use any valid selector in lieu of "SELECT".
	
	
	Events:
		
		The focus, blur, and change events fire on the *orignal* SELECT element.
	
	
	Freebies:
		
		- Includes keyboard support (tab in/out, arrows, page up/down, home/end, enter/esc)
		
		- Supports jQuery UI .ui-corner-x classes (http://jqueryui.com/docs/Theming/API#Corner_Radius_helpers)
		
		- Uses CSS3 techniques (fully customizable via CSS)
	
	
	Change log:
	
		v0.1 (2011-01-24) - Initial release
	
	
	Known issues:
		
		- The change event fires every time an option is changed using the keyboard. This differs
		  from the way change events occur on normal select elements (on blur).
		
		- Disabled controls will technically accept focus (but no event will be trigger) when tabbed 
		  over. This differs from the default browser behavior where the control would normally be 
		  skipped.
		
		- If using the keyboard while the mouse is hovering over the dropdown, the hover events
		  sometimes conflict making it seem like the keyboard selection is buggy (move the mouse 
		  out and the behavior goes away)
		  
		- The plugin cannot poll for changes to the original control (i.e. disabling it dynamically). 
		  Since the dropdown gets re-generated each time it is shown, this isn't an issue with 
		  optgroups and options. Calling scripts should be aware of this.
		  
		- Safari doesn't currently allow focus via tabbing (Chrome does; possible WebKit bug?)
		  
		- Does not support multiple="multiple"
		 
		- Not tested in IE6
	
		
	Wish list:
		
		- Enforce that dropdowns always appear in the viewport
		
		- Predictive selection (auto-selecting of elements while typing)
		  
		  Issue: keypress doesn't fire on non-input elements (only in Firefox, 
		  but this is against the standard), so we have to use the keydown event.
		  There isn't a reliable way to map extended (i.e. non-ASCII) characters 
		  without using the keypress event.
		  
		  Aside from that, it should be easy enough to set a timer that waits 
		  about two seconds after each keystroke before clearing the filter. 
		  Then we just select the first option that matches the filter. This
		  feature should be available with or without the dropdown showing.
	
*/
if(jQuery) (function($) {
	
	$.extend($.fn, {
		
		select: function(o, data) {
			
			
			var _show = function(event) {
				
				var select = event.data.select;
				var control = event.data.control;
				
				// Don't show disabled controls
				if( $(control).hasClass('ui-selectBox-disabled') ) return false;
				
				// Hide if the control is selected when the dropdown is already open
				if( $(control).hasClass('ui-selectBox-focus') && $("#ui-selectBox-dropdown").size() === 1 ) {
					_hide(event, true);
					return false;
				}
				
				// Remove focus and dropdown from any/all other selectBoxes
				$('.ui-selectBox').not(control).trigger('blur');
				
				_focus(event);
				
				event.stopPropagation();
				
				// Generate the dropdown
				$("#ui-selectBox-dropdown").remove();
				var dropdown = $('<div id="ui-selectBox-dropdown" class="ui-corner-bottom" />');
				var options = $('<ul />');
				
				if( $(select).children('optgroup').size() === 0 ) {
				
					$(select).children('option').each( function() {
						var text = $(this).text() !== '' ? $(this).text() : '\u00A0';
						var extraClasses = '';
						if( $(this).attr('disabled') ) extraClasses += ' ui-selectBox-disabled';
						$(options).append('<li class="ui-selectBox-option' + extraClasses + '">' + text + '</li>');
					});
				
				} else {
					
					$(dropdown).addClass('ui-selectBox-hasOptgroups');
					
					$(select).children('optgroup').each( function() {
						$(options).append('<li class="ui-selectBox-optgroup">' + $(this).attr('label') + '</li>');
						$(this).children('option').each( function() {
							var text = $(this).text() !== '' ? $(this).text() : '\u00A0';
							var extraClasses = '';
							if( $(this).attr('disabled') ) extraClasses += ' ui-selectBox-disabled';
							$(options).append('<li class="ui-selectBox-option' + extraClasses + '">' + text + '</li>');
						});
					});
					
				}
				
				// Add the options
				$(dropdown).append(options);
				
				// Select the appropriate option
				var selectedIndex = $(select)[0].selectedIndex;
				$(dropdown).find('LI.ui-selectBox-option').eq(selectedIndex).addClass('ui-selectBox-initial ui-selectBox-current');
				
				// Add option events
				$(dropdown).find('LI.ui-selectBox-option').hover( function() {
					$(dropdown).find('.ui-selectBox-current').removeClass('ui-selectBox-current');
					$(this).addClass('ui-selectBox-current');
				}, function() {
					$(this).removeClass('ui-selectBox-current');
				}).click( { select: select, control: control }, function(event) {
					_select(event);
				}).mouseup( { select: select, control: control }, function(event) {
					$(event.target).trigger('click');
				});				
				
				// Position and display
				$('BODY').append(dropdown);
				var cPos = $(control).offset();
				var cHeight = $(control).outerHeight();
				var cWidth = $(control).outerWidth();
				
				var borderAdjustment = parseInt($(dropdown).css('borderLeftWidth')) + parseInt($(dropdown).css('borderRightWidth'));
				
				$(dropdown).css({
					position: 'absolute',
					zIndex: '999999',
					top: cPos.top + cHeight,
					left: cPos.left,
					width: cWidth - borderAdjustment
				}).show();
				
				$(control).removeClass('ui-corner-all').addClass('ui-corner-top');
				
				_disableSelection(dropdown);
				_dropdownScrollFix(true);
				
			};
			
			
			var _hide = function(event, preventBlur) {
				
				var select = event.data.select;
				var control = event.data.control;
				
				$("#ui-selectBox-dropdown").remove();
				$(control).removeClass('ui-corner-top').addClass('ui-corner-all');
				
				if( !preventBlur ) {
					_blur(event);
				} else {
					$(control).focus();
				}
				
			};
			
			
			var _select = function(event, option) {
				
				var select = event.data.select;
				var control = event.data.control;				
				
				option = option ? option : event.target;
				
				if( $(option).hasClass('ui-selectBox-disabled') ) return false;
				
				var oldSelectedIndex = $(select)[0].selectedIndex;
				$('#ui-selectBox-dropdown .ui-selectBox-optgroup').remove();
				var newSelectedIndex = $('#ui-selectBox-dropdown').find('LI.ui-selectBox-current').index();				
				
				if( oldSelectedIndex !== newSelectedIndex ) {
					$(select)[0].selectedIndex = newSelectedIndex;
					$(control).find('.ui-selectBox-label').text( $(option).text() );
					$(select).trigger('change');
				}
				
				_hide(event, true);
				
			};
			
			
			var _focus = function(event) {
				
				var select = event.data.select;
				var control = event.data.control;				
				
				if( $(control).hasClass('ui-selectBox-disabled') ) return true;
				if( $(control).hasClass('ui-selectBox-focus') ) return false;
				
				// Remove dropdown and other focuses
				$(".ui-selectBox.ui-selectBox-focus").removeClass("ui-selectBox-focus");
				$("#ui-selectBox-dropdown").remove();
				
				$(control).addClass('ui-selectBox-focus');
				$(document).bind('mousedown', { select: select, control: control }, _blur);
				$(document).bind('keydown', { select: select, control: control }, _key);
				$(select).trigger('focus');
				$(control).focus();
				
			};
			
			
			var _blur = function(event) {
				
				var select = event.data.select;
				var control = event.data.control;
				
				// Prevent blur if the click was on the dropdown
				if( event.target.id === 'ui-selectBox-dropdown' || 
					$(event.target).parents('#ui-selectBox-dropdown').size() === 1 ) {
					$(control).trigger('focus');
					return false;
				}
				
				if( $(control).hasClass('ui-selectBox-focus') ) {
					$(control).removeClass('ui-selectBox-focus');
					$(document).unbind('mousedown', _blur);
					$(document).unbind('keydown', _key);
					$(select).trigger('blur');
					_hide(event);
				}
				
			};
			
			
			var _key = function(event) {
				
				var select = event.data.select;
				var control = event.data.control;
				var dropdown = $("#ui-selectBox-dropdown");
				
				if( $(control).hasClass('ui-selectBox-disabled') ) return false;
				
				switch( event.keyCode ) {
					
					case 9: // tab
						_blur(event);
						break;
					
					case 13: // enter
						
						if( $(dropdown).size() === 0 ) return false;
						
						var siblings = $(dropdown).find('.ui-selectBox-option');
						var currentIndex = -1;
						$.each(siblings, function(index, option) {
							if( $(option).hasClass('ui-selectBox-current') ) {
								currentIndex = index;
								return;
							}
						});
						
						if( currentIndex >= 0 ) {
							_select(event, $(siblings).eq(currentIndex));
						}
						
						return false;
						
						break;
						
					case 27: // esc
						_hide(event, true);
						break;
						
					case 38: // up
					case 37: // left
					case 33: // page up
						
						var interval = event.keyCode === 33 ? 20 : 1;
						
						if( $(dropdown).size() === 0 ) {
							
							if( event.altKey ) {
								_show(event);
								return false;
							}
							
							// Previous selection
							var totalIndexes = $(select).find('OPTION').size(),
								oldSelectedIndex = $(select)[0].selectedIndex,
								newSelectedIndex = $(select)[0].selectedIndex - interval;
							
							// Look for non-disabled option
							while( $(select).find('OPTION').eq(newSelectedIndex).attr('disabled') === true && newSelectedIndex >= 0 ) {
								newSelectedIndex--;
							}
							
							// Look for first enabled option
							if( newSelectedIndex < 0 ) {
								newSelectedIndex = $(select).find('OPTION:not([disabled]):first').index();
							}
							
							$(select)[0].selectedIndex = newSelectedIndex;
							if( $(select)[0].selectedIndex === -1 ) {
								newSelectedIndex = 0;
								$(select)[0].selectedIndex = newSelectedIndex;
							}
							var label = $(select).find('OPTION:selected').text();
							if( label === '' ) label = '\u00A0'; // &nbsp;
							$(control).find('.ui-selectBox-label').text(label);
							
							if( newSelectedIndex !== oldSelectedIndex ) $(select).trigger('change');
							
							return false;
							
						}
						
						// Determine currently selected index (ignoring optgroup LIs)
						var siblings = $(dropdown).find('.ui-selectBox-option');
						var currentIndex = -1;
						$.each(siblings, function(index, option) {
							if( $(option).hasClass('ui-selectBox-current') ) {
								currentIndex = index;
								return;
							}
						});
						
						currentIndex = currentIndex - interval;
						if( currentIndex < 0 ) currentIndex = 0;
						
						$(siblings).removeClass('ui-selectBox-current');
						$(siblings).eq(currentIndex).addClass('ui-selectBox-current');						
						
						_dropdownScrollFix();
						
						return false;
						
						break;
						
					case 40: // down
					case 39: // right
					case 34: // page down
						
						var interval = event.keyCode === 34 ? 20 : 1;
						
						if( $(dropdown).size() === 0 ) {
							
							if( event.altKey ) {
								_show(event);
								return false;
							}
							
							var totalIndexes = $(select).find('OPTION').size(),
								oldSelectedIndex = $(select)[0].selectedIndex,
								newSelectedIndex = $(select)[0].selectedIndex + interval;
							
							// Look for non-disabled option
							while( $(select).find('OPTION').eq(newSelectedIndex).attr('disabled') === true  && newSelectedIndex <= $(select).find('OPTION').size() ) {
								newSelectedIndex++;
							}
							
							// Look for last enabled option
							if( newSelectedIndex > totalIndexes - 1 ) {
								newSelectedIndex = $(select).find('OPTION:not([disabled]):last').index();
							}
							
							$(select)[0].selectedIndex = newSelectedIndex;
							if( $(select)[0].selectedIndex === -1 ) {
								newSelectedIndex = $(select).find('OPTION').size() - 1;
								$(select)[0].selectedIndex = newSelectedIndex;
							}
							var label = $(select).find('OPTION:selected').text();
							if( label === '' ) label = '\u00A0'; // &nbsp;
							$(control).find('.ui-selectBox-label').text(label);
							
							if( newSelectedIndex != oldSelectedIndex ) $(select).trigger('change');
							
							return false;
							
						}
						
						// Determine currently selected index (ignoring optgroup LIs)
						var siblings = $(dropdown).find('.ui-selectBox-option');
						var currentIndex = -1;
						$.each(siblings, function(index, option) {
							if( $(option).hasClass('ui-selectBox-current') ) {
								currentIndex = index;
								return;
							}
						});
						
						currentIndex = currentIndex + interval;
						if( currentIndex > $(siblings).size() - 1 ) currentIndex = $(siblings).size() - 1;
						
						$(siblings).removeClass('ui-selectBox-current');
						$(siblings).eq(currentIndex).addClass('ui-selectBox-current');
						
						_dropdownScrollFix();
						
						return false;
						
						break;
						
					case 36: // home
					case 35: // end
						
						if( $(dropdown).size() === 0 ) {
							
							if( event.altKey ) {
								_show(event);
								return false;
							}
							
							var oldSelectedIndex = $(select)[0].selectedIndex,
								newSelectedIndex;
							
							if( event.keyCode === 36 ) {
								// First
								newSelectedIndex = 0;
							} else {
								// Last
								newSelectedIndex = $(select).find('OPTION').size() - 1;
							}
							
							// Handle disabled options
							if( $(select).find('OPTION').eq(newSelectedIndex).attr('disabled') === true ) {
								if( event.keyCode === 36 ) {
									newSelectedIndex = $(select).find('OPTION:not([disabled]):first').index();	
								} else {
									newSelectedIndex = $(select).find('OPTION:not([disabled]):last').index();
								}
							}
							
							$(select)[0].selectedIndex = newSelectedIndex;
							var label = $(select).find('OPTION:selected').text();
							if( label === '' ) label = '\u00A0'; // &nbsp;
							$(control).find('.ui-selectBox-label').text(label);
							
							if( newSelectedIndex != oldSelectedIndex ) $(select).trigger('change');
							
							return false;
						}					
						
						$(dropdown).find('.ui-selectBox-current').removeClass('ui-selectBox-current');
						if( event.keyCode === 36 ) {
							// First
							$(dropdown).find('.ui-selectBox-option:first').addClass('ui-selectBox-current');
						} else {
							// Last
							$(dropdown).find('.ui-selectBox-option:last').addClass('ui-selectBox-current');
						}
						
						_dropdownScrollFix();
						
						return false;
						
						break;
						
				}
				
			};
			
			
			var _dropdownScrollFix = function(centerSelection) {
				
				var dropdown = $("#ui-selectBox-dropdown");
				if( $(dropdown).size() === 0 ) return false;
				
				var target = $(dropdown).find('.ui-selectBox-current');
				if( $(target).size() === 0 ) return false;
				
				var targetTop = parseInt($(target).offset().top - $(dropdown).position().top);
				var targetBottom = parseInt(targetTop + $(target).outerHeight());
				
				if( centerSelection ) {
					
					$(dropdown).scrollTop(
						$(target).offset().top - $(dropdown).offset().top + $(dropdown).scrollTop() - ($(dropdown).height() / 2)
					);
					
				} else {
				
					if( targetTop < 0 ) {
						$(dropdown).scrollTop(
							$(target).offset().top - $(dropdown).offset().top + $(dropdown).scrollTop()
						);
					}
					
					if( targetBottom > $(dropdown).height() ) {
						$(dropdown).scrollTop(
							($(target).offset().top + $(target).outerHeight() ) - $(dropdown).offset().top + $(dropdown).scrollTop() - $(dropdown).height()
						);
					}
				
				}
				
			};
			
			
			var _disableSelection = function(selector) {
				
				$(selector)
					.css('MozUserSelect', 'none')
					.bind('selectstart', function() {
						return false;
					})
					.bind('mousedown', function() {
						return false;
					});
				
				return true;
				
			};
			
			
			switch( o ) {
				
				
				case 'destroy':
					
					$(this).each( function() {
						
						var select = $(this);
						var control = $(this).next('.ui-selectBox');
						
						if( $(select)[0].tagName.toLowerCase() === 'select' ) {
							$(control).remove();
							$(select).show();
						}
						
					});
					
					return $(this);
					
					break;
				
				
				case 'disable':
					
					$(this).each( function() {
						
						var select = $(this);
						var control = $(this).next('.ui-selectBox');
						
						$(select).attr('disabled', true);
						$(control).addClass('ui-selectBox-disabled');
						
					});
					
					return $(this);
					
					break;
				
				
				case 'enable':
					
					$(this).each( function() {
						
						var select = $(this);
						var control = $(this).next('.ui-selectBox');
						
						$(select).attr('disabled', false);
						$(control).removeClass('ui-selectBox-disabled');
						
					});
					
					return $(this);
					
					break;				
				
				
				default:
					
					// Create the control
					$(this).each( function() {
						
						var select = $(this);
						
						if( $(this).next('.ui-selectBox').size() === 0 ) {
							
							// Generate new control
							var control = $('<a href="#" class="ui-selectBox ui-corner-all" tabindex="' + parseInt($(select).attr('tabindex')) + '" />');
							
							if( $(select)[0].tagName.toLowerCase() !== 'select' || $(select).attr('multiple') === true ) return;
							if( $(select).attr('disabled') === true ) $(control).addClass('ui-selectBox-disabled');
							
							var label = $(select).find('OPTION:selected').text();
							if( label === '' ) label = '\u00A0'; // &nbsp;
							
							// Add label and arrow
							$(control).append('<span class="ui-selectBox-label">' + label + '</span>');
							$(control).append('<span class="ui-selectBox-arrow"></span>');
							$(select).hide().after(control);
							
							_disableSelection(control);
							
							$(control)
								.bind('click', function() { return false; })
								.bind('mousedown', { select: select, control: control }, _show)
								.bind('focus', { select: select, control: control }, _focus)
								.bind('blur', { select: select, control: control }, _blur);
							
						}
						
					});
					
					return $(this);
					
					break;
				
			}

				
		}
		
			
	});
	
})(jQuery);