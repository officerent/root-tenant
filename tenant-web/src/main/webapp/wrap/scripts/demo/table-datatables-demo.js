(function(){
  'use strict';

  // Table Tools
  // tabletools setting
  /*
   * TableTools Bootstrap compatibility
   * Required TableTools 2.1+
   */
   if ( $.fn.DataTable.TableTools ) {
    // Set the classes that TableTools uses to something suitable for Bootstrap
    $.extend( true, $.fn.DataTable.TableTools.classes, {
      'container': 'DTTT btn-group',
      'buttons': {
        'normal': 'btn btn-default',
        'disabled': 'disabled'
      },
      'collection': {
        'container': 'DTTT_dropdown dropdown-menu',
        'buttons': {
          'normal': '',
          'disabled': 'disabled'
        }
      },
      'print': {
        'info': 'DTTT_print_info'
      },
      'select': {
        'row': 'selected'
      }
    } );

    // Have the collection use a bootstrap compatible dropdown
    $.extend( true, $.fn.DataTable.TableTools.DEFAULTS.oTags, {
      'collection': {
        'container': 'ul',
        'button': 'li',
        'liner': 'a'
      }
    } );
  }
  // datatables2
  $('#datatables2').dataTable({
    'autoWidth': false,
    'sDom': '<"TTT_btn-group-wrapper tt-actions-demo"T><"row"<"col-sm-12"<"pull-right"f><"pull-left"l>r<"clearfix">>>t<"row"<"col-sm-12"<"pull-left"i><"pull-right"p><"clearfix">>>',
    // 'sPaginationType': 'two_button',
    'sAjaxSource': '/admin/feedback/list.html',
    'oTableTools': {
      'aButtons': [
      {
        'sExtends': 'copy',
        'sButtonText': '<i class="fa fa-clone fa-lg" title="Copy"></i>',
        'sButtonClass': 'btn btn-default btn-icon'
      },
      {
        'sExtends': 'print',
        'sButtonText': '<i class="icon-printer fa-lg" title="Print"></i>',
        'sButtonClass': 'btn btn-default btn-icon'
      },
      {
        'sExtends': 'collection',
        'sButtonText': '<i class="fa fa-floppy-o fa-lg" title="Save as..."></i> <i class="fa fa-angle-down"></i>',
        'sButtonClass': 'btn btn-default btn-icon',
        'aButtons': [ 'xls', 'csv', 'pdf' ]
      }
      ],
      'sSwfPath': 'scripts/swf/copy_csv_xls_pdf.swf'
    },
    'fnInitComplete': function(settings) {
      var $wrapperTable = $(settings.nTable).closest('.dataTables_wrapper');

      // replace select
      $wrapperTable.find('.dataTables_length select').wrap('<label class="select select-inline select-sm"></label>');

      // place tableTools buttons to panel-actions
      $('.tt-actions-demo').prependTo('#tt-actions');

      var $nTable = $(settings.nTable);
      $nTable.closest( '.dataTables_wrapper' ).find( '.dataTable' ).wrap('<div class="table-responsive"></div>');
    }
  });



  // adding custom styles to all .datatables
  $('.dataTable').each(function(){
    var datatables = $( this );
    // SEARCH - Add the placeholder for Search and Turn this into in-line form control
    var searchInput = datatables.closest('.dataTables_wrapper').find('div[id$=_filter] input');
    searchInput.attr('placeholder', 'Search');
  });

})(window);