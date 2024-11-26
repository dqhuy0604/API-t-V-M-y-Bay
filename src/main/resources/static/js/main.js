(function ($) {
    "use strict";
    
    // Dropdown on mouse hover
    $(document).ready(function () {
        function toggleNavbarMethod() {
            if ($(window).width() > 992) {
                $('.navbar .dropdown').on('mouseover', function () {
                    $('.dropdown-toggle', this).trigger('click');
                }).on('mouseout', function () {
                    $('.dropdown-toggle', this).trigger('click').blur();
                });
            } else {
                $('.navbar .dropdown').off('mouseover').off('mouseout');
            }
        }
        toggleNavbarMethod();
        $(window).resize(toggleNavbarMethod);
    });
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Date and time picker

    $(document).ready(function () {
        // Chỉ hiển thị ngày
        $('.date').datetimepicker({
            format: 'DD/MM/YYYY', // Định dạng chỉ ngày (ngày/tháng/năm)
            useCurrent: false,    // Không tự động chọn ngày hiện tại
            icons: {
                time: 'fas fa-clock',
                date: 'fas fa-calendar',
                up: 'fas fa-arrow-up',
                down: 'fas fa-arrow-down',
                previous: 'fas fa-chevron-left',
                next: 'fas fa-chevron-right',
                today: 'fas fa-calendar-check',
                clear: 'fas fa-trash',
                close: 'fas fa-times'
            }
        });
        
    
        // Xóa cấu hình thời gian nếu không cần
        $('.time').datetimepicker('destroy');
    });




// Add event listeners to the radio buttons
    oneWayRadio.addEventListener('change', toggleReturnDate);
    roundTripRadio.addEventListener('change', toggleReturnDate);
    multiCityRadio.addEventListener('change', toggleReturnDate);

// Initial check
    toggleReturnDate();


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        margin: 30,
        dots: true,
        loop: true,
        center: true,
        responsive: {
            0:{
                items:1
            },
            576:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:3
            }
        }
    });




//Delete register's image
const defaultImage = "https://via.placeholder.com/300x400";
    
        function previewImage(event) {
            const input = event.target;
            const preview = document.getElementById('preview-image');
            const removeButton = document.getElementById('remove-button');
    
            if (input.files && input.files[0]) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    preview.src = e.target.result;
                    removeButton.style.display = "inline-block"; // Hiển thị nút xóa
                };
                reader.readAsDataURL(input.files[0]);
            }
        }
    
        function removeImage() {
            const preview = document.getElementById('preview-image');
            const uploadInput = document.getElementById('image-upload');
            const removeButton = document.getElementById('remove-button');
    
            preview.src = defaultImage; // Quay lại ảnh mặc định
            uploadInput.value = ""; // Xóa file đã chọn
            removeButton.style.display = "none"; // Ẩn nút xóa
        }


    
})(jQuery);

